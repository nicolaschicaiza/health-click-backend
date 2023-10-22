package com.tadhack.healthclick.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.tadhack.healthclick.model.EnfermedadEntity;
import com.tadhack.healthclick.model.PacienteEntity;
import com.tadhack.healthclick.service.EnfermedadService;
import com.tadhack.healthclick.service.PacienteService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "http://localhost:4200")
public class ReporteController {

        @Autowired
        private PacienteService pacienteService;

        @Autowired
        private EnfermedadService enfermedadService;

        @GetMapping("/reporte")
        public void ReporteJasper(HttpServletResponse response) throws InvalidFormatException, IOException, JRException {
//inicio configuracion jasper resport

                List<PacienteEntity> pacientes = this.pacienteService.listarPacientes();
                List<EnfermedadEntity> enfermedades = this.enfermedadService.ListarEnfermedades();
                JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/static/jasper" +
                        "/recetario_hadtack.jasper"));
                JRBeanCollectionDataSource dataDisease = new JRBeanCollectionDataSource(enfermedades);
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("cedula",pacientes.get(0).getCedula());
                parameters.put("nombre",pacientes.get(0).getNombre());
                parameters.put("edad",pacientes.get(0).getEdad());
                parameters.put("ocupacion",pacientes.get(0).getOcupacion());
                parameters.put("fecha_nacimiento",pacientes.get(0).getFecha_nacimiento());
                parameters.put("sexo",pacientes.get(0).getSexo());



                JasperPrint jprint = JasperFillManager.fillReport(report, parameters, dataDisease);
                //JRDocxExporter exporter = new JRDocxExporter();
                JRPdfExporter exporter = new JRPdfExporter();

                //String headerKey = "Content-Disposition";
                //String headerValue = "attachment; filename=ejemplo.docx";
                //response.setHeader(headerKey, headerValue);
                //JasperExportManager.exportReportToPdfStream(jprint,response.getOutputStream());

                exporter.setExporterInput(new SimpleExporterInput(jprint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
                response.setHeader("Content-Disposition", "attachment;filename=example.pdf");
                exporter.exportReport();
        }
}
