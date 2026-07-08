package relatorio;


import dao.AluguelDAO;
import model.Aluguel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.font.PDType1Font;



public class GerarRelatorioAluguel {


    public static void gerar(){


        try{


            AluguelDAO dao = new AluguelDAO();

            List<Aluguel> lista = dao.listar();



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

            conteudo.newLineAtOffset(50,y);

            conteudo.showText("Relatorio de Alugueis");

            conteudo.endText();



            y -= 40;



            for(Aluguel aluguel : lista){


                conteudo.beginText();


                conteudo.setFont(
                    new PDType1Font(Standard14Fonts.FontName.HELVETICA),
                    12
                );


                conteudo.newLineAtOffset(50,y);


                conteudo.showText(
                    "ID: " + aluguel.getIdAluguel()
                    + " Cliente: " + aluguel.getNomeCliente()
                    + " PC: " + aluguel.getIdComputador()
                    + " Horas: " + aluguel.getQtdHoras()
                );


                conteudo.endText();


                y -= 20;

            }


            conteudo.close();


            documento.save("Relatorio_Alugueis.pdf");


            documento.close();



            JOptionPane.showMessageDialog(
                null,
                "Relatorio de alugueis gerado!"
            );


        }catch(IOException | SQLException e){


            JOptionPane.showMessageDialog(
                null,
                e.getMessage()
            );


        }


    }


}