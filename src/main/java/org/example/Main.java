package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_URL = "jdbc:mysql://localhost/preguntas_test";
    static final String USER = "root";
    static final String PASS = "";
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            int opcio = 0;
            System.out.println("1: Afegir una pregunta amb 2 possibles respostes d’un tema que entrem per teclat varchar. ");
            System.out.println("2: Contestar totes les preguntes d’un tema en concret (haureu de mostrar la pregunta i les possibles respostes) i que al final et digui quantes has fallat i quantes has endevinat. ");
            System.out.println("3: Mostrar totes les preguntes amb la seva resposta correcta al costat, tant la pregunta com la resposta ha de ser el camp varchar. ");
            System.out.println("4: Mostrar el nombre de preguntes que hi ha per tema. ");
            System.out.println("5: Afegir una resposta incorrecta a una pregunta on entrem el id per teclat. ");
            System.out.println("6: Sortir");
            System.out.println("Quina opcio vols ficar? ");
            opcio = scan.nextInt();
            scan.nextLine();

            //Exercisi 1
            if (opcio == 1) { //No va
                System.out.println("Introdueix el tema: ");
                String tema = scan.nextLine();
                String sql = "SELECT tema.idtema , tema.tema FROM tema WHERE tema.tema = ? ;";
                ResultSet rs = stmt.executeQuery(sql);
                PreparedStatement prp = conn.prepareStatement(sql);
                prp.setString(1,tema);
                System.out.println(sql);

                if (rs.next() == false) { //No hi ha tema
                    System.out.println("Introdueix el idtema: ");
                    int idtema = scan.nextInt();scan.nextLine();
                    String sql2 = "INSERT INTO tema(idtema, tema) VALUES (?,?)";
                    PreparedStatement prp2 = conn.prepareStatement(sql2);
                    prp2.setInt(1, idtema);
                    prp2.setString(2, tema);
                    prp2.executeUpdate(sql2);

                }else { // Si hi ha tema
                    ResultSet rs2 = stmt.executeQuery(sql);
                    while (rs2.next()) {
                        System.out.println("---------------------------------------------");
                        System.out.print("idtema: " + rs.getInt("idtema"));
                        System.out.println(", tema: " + rs.getString("tema"));
                        System.out.println("---------------------------------------------");
                    }
                }
                //Pillar les dades
                System.out.print("Introdueix la ID de la pregunta:");
                int idpregunta = scan.nextInt();
                scan.nextLine();

                System.out.print("Introdueix la pregunta:");
                String pregunta = scan.nextLine();

                System.out.print("Introdueix el numero de la resposta correcta:");
                Integer correcta = scan.nextInt();
                scan.nextLine();

                System.out.print("Introdueix una resposta en text: ");
                String respuesta = scan.nextLine();

                System.out.print("Introdueix el id d' una resposta: ");
                Integer idrespuesta = scan.nextInt();
                scan.nextLine();

                System.out.print("Introdueix l' altra resposta en text: ");
                String respuesta2 = scan.nextLine();

                System.out.print("Introdueix el id de l' altra resposta: ");
                Integer idrespuesta2 = scan.nextInt();
                scan.nextLine();

                //Pregunta
                String sql3 = "INSERT INTO `pregunta`(`idpregunta`, `pregunta`, `correcta`) VALUES (?,?,?)";
                PreparedStatement prp3 = conn.prepareStatement(sql3);
                prp3.setInt(1, idpregunta);
                prp3.setString(2, pregunta);
                prp3.setInt(3, correcta);
                prp3.executeUpdate(sql3);
                System.out.println(sql3);
                ResultSet rs3 = stmt.executeQuery(sql3);

                while (rs3.next()) {
                    System.out.println("---------------------------------------------");
                    System.out.println("Pregunta: ");
                    System.out.print("idpregunta: " + rs3.getInt("idpregunta"));
                    System.out.println(", pregunta: " + rs3.getString("pregunta"));
                    System.out.println(", correcta: " + rs3.getInt("correcta"));
                    System.out.println("---------------------------------------------");
                }

                //Primera resposta

                int pregunta_idpregunta = idpregunta;
                String sql4 = "INSERT INTO `respuesta`(`pregunta_idpregunta`, `idrespuesta`, `respuesta`) VALUES (?,?,?)";
                ResultSet rs4 = stmt.executeQuery(sql4);
                PreparedStatement prp4 = conn.prepareStatement(sql4);
                prp4.setInt(1, pregunta_idpregunta);
                prp4.setInt(2, idrespuesta);
                prp4.setString(3, respuesta);
                prp4.executeUpdate(sql4);
                System.out.println(sql4);

                while (rs4.next()) {
                    System.out.println("---------------------------------------------");
                    System.out.println("Primera resposta: ");
                    System.out.print("pregunta_idpregunta: " + rs4.getInt("pregunta_idpregunta"));
                    System.out.println(", idrespuesta: " + rs4.getInt("idrespuesta"));
                    System.out.println(", respuesta: " + rs4.getString("respuesta"));
                    System.out.println("---------------------------------------------");
                }

                //Segona resposta

                String sql5 = "INSERT INTO `respuesta`(`pregunta_idpregunta`, `idrespuesta`, `respuesta`) VALUES (?,?,?)";
                ResultSet rs5 = stmt.executeQuery(sql3);
                PreparedStatement prp5 = conn.prepareStatement(sql5);
                prp5.setInt(1, pregunta_idpregunta);
                prp5.setInt(2, idrespuesta2);
                prp5.setString(3, respuesta2);
                prp5.executeUpdate(sql5);
                System.out.println(sql5);

                while (rs5.next()) {
                    System.out.println("---------------------------------------------");
                    System.out.println("Segona resposta: ");
                    System.out.print("pregunta_idpregunta: " + rs5.getInt("pregunta_idpregunta"));
                    System.out.println(", idrespuesta2: " + rs5.getInt("idrespuesta2"));
                    System.out.println(", respuesta2: " + rs5.getString("respuesta2"));
                    System.out.println("---------------------------------------------");
                }

            }else if (opcio == 2){ //No va

                //Mostrar temes
                System.out.print("Introdueix el idtema:");
                int idtema = scan.nextInt();
                scan.nextLine();
                System.out.print("Introdueix el tema: ");
                String tema = scan.nextLine();

                String sql = "INSERT INTO `tema` (`idtema`, `tema`) VALUES (?,?)";
                System.out.println(sql);
                PreparedStatement prp = conn.prepareStatement(sql);
                prp.setInt(1,idtema);
                prp.setString(2,tema);
                prp.executeUpdate();

                String QUERY = "SELECT * FROM tema";
                ResultSet rs = stmt.executeQuery(QUERY);

                while (rs.next()) {
                    System.out.println("------------------------------------");
                    System.out.print("idpregunta: " + rs.getInt("idpregunta"));
                    System.out.print(", pregunta: " + rs.getString("pregunta"));
                    System.out.println(", correcta: " + rs.getString("correcta"));
                    int correcta = rs.getInt("correcta");
                    System.out.println("------------------------------------");
                    QUERY = "SELECT * FROM respuesta WHERE respuesta.pregunta_idpregunta = "+rs.getInt(correcta)+"";
                    Statement stmt2 = conn.createStatement();
                    ResultSet rs2 = stmt2.executeQuery(QUERY);
                    while (rs2.next()) {
                        System.out.println("------------------------------------");
                        System.out.println("idrespuesta: " + rs.getInt("idrespuesta"));
                        System.out.println("respuesta: " + rs.getString("respuesta"));
                        System.out.println("------------------------------------");
                    }
                    System.out.println("Introdueix la resposta: ");
                    int resposta = scan.nextInt();scan.nextLine();
                    if (resposta == correcta) {
                        System.out.println("Be");
                    }else{
                        System.out.println("Malament");
                    }
                }
            }else if (opcio == 3){ // fet

                    try(PreparedStatement pstmt = conn.prepareStatement("SELECT pregunta.pregunta, respuesta.respuesta AS resposta FROM pregunta INNER JOIN respuesta ON pregunta.idpregunta = respuesta.pregunta_idpregunta WHERE pregunta.correcta = respuesta.idrespuesta;");) {
                        ResultSet rs = pstmt.executeQuery();

                        while (rs.next()) {
                            System.out.println(rs.getString("pregunta"));
                            System.out.println(rs.getString("resposta"));
                        }
                    }

                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Potser que hi haguin preguntes amb respostes que no tenen sentit, es que les vaig insertar malament o a potra");
            }else if (opcio == 4){ // fet

                try(PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) AS preguntes, tema_has_pregunta.tema_idtema AS idtema FROM pregunta INNER JOIN tema_has_pregunta " +
                        "ON pregunta.idpregunta = tema_has_pregunta.tema_idtema GROUP BY tema_has_pregunta.tema_idtema;");) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        System.out.println("Hi han: " +(rs.getString("preguntes") + " preguntes del tema: " + (rs.getString("idtema"))));
                    }
                }

                catch (SQLException e) {
                    e.printStackTrace();
                }

            }else if (opcio == 5){ //fet
                System.out.println("Has de ficar un idpregunta que ja existeixi o no furule");
                System.out.print("Introdueix la ID de la pregunta:");
                int idpregunta = scan.nextInt();
                scan.nextLine();

                System.out.print("Introdueix el idresposta:");
                Integer idresposta = scan.nextInt();
                scan.nextLine();

                System.out.print("Introdueix el texte de la resposta:");
                String resposta = scan.nextLine();

                try{
                    String query = "INSERT INTO respuesta(pregunta_idpregunta, idrespuesta, respuesta) VALUES (?,?,?)";

                    PreparedStatement pstmt = conn.prepareStatement(query);

                    pstmt.setInt(1, idpregunta);
                    pstmt.setInt(2, idresposta);
                    pstmt.setString(3, resposta);
                    pstmt.executeUpdate();

                    /*ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        System.out.println("De l' idpregunta: " +(rs.getInt("idpregunta") + " , li has insertat una resposta amb l' id: : " + (rs.getInt("idresposta") + " ,i la resposta en texte es: "
                                +(rs.getString("resposta")))));
                    }*/



                }catch(Exception e){
                    e.printStackTrace();
                }


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}