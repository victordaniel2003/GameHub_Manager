package relatorio;

import dao.ClienteDAO;
import model.Cliente;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.sql.SQLException;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import javax.swing.JOptionPane;


public class GerarRelatorioCliente {


    public static void gerar() {

        try {

            ClienteDAO dao = new ClienteDAO();

            List<Cliente> lista = dao.listar();


            PDDocument documento = new PDDocument();

            PDPage pagina = new PDPage();

            documento.addPage(pagina);


            PDPageContentStream conteudo =
                    new PDPageContentStream(documento, pagina);


            conteudo.beginText();

            conteudo.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);

            conteudo.newLineAtOffset(50, 750);

            conteudo.showText("Relatorio de Clientes");

            conteudo.endText();


            int y = 700;


            for (Cliente cliente : lista) {


                conteudo.beginText();

                conteudo.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);

                conteudo.newLineAtOffset(50, y);


                conteudo.showText(
                    "ID: " + cliente.getId()
                    + " | Nome: " + cliente.getNome()
                    + " | Email: " + cliente.getEmail()
                );


                conteudo.endText();


                y -= 20;


                if(y < 50){

                    conteudo.close();

                    pagina = new PDPage();

                    documento.addPage(pagina);

                    conteudo =
                    new PDPageContentStream(documento, pagina);

                    y = 750;
                }

            }


            conteudo.close();


            documento.save("Relatorio_Clientes.pdf");

            documento.close();


            JOptionPane.showMessageDialog(
                null,
                "Relatório gerado com sucesso!"
            );


        } catch (IOException | SQLException e) {

            JOptionPane.showMessageDialog(
            null,
            e.getMessage()
            );

        }
    }

}