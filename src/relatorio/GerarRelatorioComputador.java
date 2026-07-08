package relatorio;

import dao.ComputadorDAO;
import model.Computador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


public class GerarRelatorioComputador {


    public static void gerar() {

        try {

            ComputadorDAO dao = new ComputadorDAO();

            List<Computador> lista = dao.listar();


            PDDocument documento = new PDDocument();

            PDPage pagina = new PDPage();

            documento.addPage(pagina);


            PDPageContentStream conteudo =
                    new PDPageContentStream(documento, pagina);


            int y = 750;


            conteudo.beginText();
            conteudo.setFont(
                new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD),
                16
            );
            conteudo.newLineAtOffset(50, y);
            conteudo.showText("Relatorio de Computadores");
            conteudo.endText();


            y -= 40;


            for (Computador pc : lista) {


                conteudo.beginText();

                conteudo.setFont(
                    new PDType1Font(Standard14Fonts.FontName.HELVETICA),
                    12
                );

                conteudo.newLineAtOffset(50, y);

                conteudo.showText(
                    "ID: " + pc.getId()
                    + " | Status: " + pc.getStatus()
                );

                conteudo.endText();


                y -= 20;

            }


            conteudo.close();

            documento.save("Relatorio_Computadores.pdf");

            documento.close();


            JOptionPane.showMessageDialog(
                null,
                "Relatorio de computadores gerado!"
            );


        } catch (IOException | SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }

}