package com.Jongyeol.JongyeolWeb.t21c;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/t21c")
public class T21CController {
    @GetMapping("")
    public String allLevels() {
        StringBuilder data = new StringBuilder("""
                <!DOCTYPE html>
                <html>
                <head>
                    <title>T21+C</title>
                    <link rel="shortcut icon" href="/t21c/t21c.ico">
                    <style type="text/css">
                table {
                    border-collapse: collapse;
                    width: 100%;
                    max-width: 800px;
                    margin: 20px auto;
                }

                table td, table th {
                    border: 1px solid #ddd;
                    padding: 8px;
                    text-align: center;
                    font-size: 14px;
                }

                table th {
                    background-color: #f2f2f2;
                }

                h1 {
                    text-align: center;
                    margin: 50px 0;
                }

                p {
                    text-align: center;
                    margin-bottom: 20px;
                    font-size: 18px;
                }
                .dif0 {
                    background-color: #efefef;
                }
                .dif1 {
                    background-color: #0099ff;
                }
                .dif2 {
                    background-color: #00bbff;
                }
                .dif3 {
                    background-color: #00ddff;
                }
                .dif4 {
                    background-color: #00ffff;
                }
                .dif5 {
                    background-color: #00ffaa;
                }
                .dif6 {
                    background-color: #00ff00;
                }
                .dif7 {
                    background-color: #66ff00;
                }
                .dif8 {
                    background-color: #99ff00;
                }
                .dif9 {
                    background-color: #ccff00;
                }
                .dif10 {
                    background-color: #ffff00;
                }
                .dif11 {
                    background-color: #ffdd00;
                }
                .dif12 {
                    background-color: #ffcc00;
                }
                .dif13 {
                    background-color: #ffaa00;
                }
                .dif14 {
                    background-color: #ff8800;
                }
                .dif15 {
                    background-color: #ff6600;
                }
                .dif16 {
                    background-color: #ff4400;
                }
                .dif17 {
                    background-color: #ff0000;
                }
                .dif18 {
                    background-color: #cc0000;
                    color: #ffffff;
                }
                .dif18_ {
                    background-color: #a61c00;
                    color: #ffffff;
                }
                .dif19 {
                    background-color: #660000;
                    color: #ffffff;
                }
                .dif19_ {
                    background-color: #460c00;
                    color: #ffffff;
                }
                .dif20_0 {
                    background-color: #431210;
                    color: #ffffff;
                }
                .dif20_0_ {
                    background-color: #41181f;
                    color: #ffffff;
                }
                .dif20_1 {
                    background-color: #3e1d2c;
                    color: #ffffff;
                }
                .dif20_1_ {
                    background-color: #3a213a;
                    color: #ffffff;
                }
                .dif20_2 {
                    background-color: #36264d;
                    color: #ffffff;
                }
                .dif20_2_ {
                    background-color: #32275a;
                    color: #ffffff;
                }
                .dif20_3 {
                    background-color: #2f2865;
                    color: #ffffff;
                }
                .dif20_3_ {
                    background-color: #2c296f;
                    color: #ffffff;
                }
                .dif20_4 {
                    background-color: #28297c;
                    color: #ffffff;
                }
                .dif20_4_ {
                    background-color: #242886;
                    color: #ffffff;
                }
                .dif20_5 {
                    background-color: #21288e;
                    color: #ffffff;
                }
                .dif20_5_ {
                    background-color: #202795;
                    color: #ffffff;
                }
                .dif20_6 {
                    background-color: #1e269d;
                    color: #ffffff;
                }
                .dif20_6_ {
                    background-color: #1d24a1;
                    color: #ffffff;
                }
                .dif20_7 {
                    background-color: #1f23a2;
                    color: #ffffff;
                }
                .dif20_7_ {
                    background-color: #1f23a2;
                    color: #ffffff;
                }
                .dif20_8 {
                    background-color: #28138f;
                    color: #ffffff;
                }
                .dif20_8_ {
                    background-color: #2b0e81;
                    color: #ffffff;
                }
                .dif20_9 {
                    background-color: #2c0a75;
                    color: #ffffff;
                }
                .dif20_9_ {
                    background-color: #2e086c;
                    color: #ffffff;
                }
                .dif21 {
                    background-color: #2f0565;
                    color: #ffffff;
                }
                .dif21_ {
                    background-color: #30045a;
                    color: #ffffff;
                }
                .dif21_1 {
                    background-color: #320355;
                    color: #ffffff;
                }
                .dif21_1_ {
                    background-color: #390f58;
                    color: #ffffff;
                }
                .dif21_2 {
                    background-color: #451f65;
                    color: #ffffff;
                }
                .dif21_2_ {
                    background-color: #53337a;
                    color: #ffffff;
                }
                .dif21_3 {
                    background-color: #55377d;
                    color: #ffffff;
                }
                .dif21_3_ {
                    background-color: #000000;
                    color: #ff0000;
                }
                .dif-1 {
                    background-color: #4c1130;
                    color: #ffffff;
                }
                .dif-2 {
                    background-color: #434343;
                    color: #ffffff;
                }
                .dif-21 {
                    background-color: #593d83;
                    color: #ffffff;
                }
                .StrengG {
                    background-color: #b7e1cd;
                }
                .StrengY {
                    background-color: #b7e1cd;
                }
                .StrengR {
                    background-color: #b7e1cd;
                }
                </style>
                </head>
                <body>
                <h1>ALL Levels</h1>
                <p>The 21+ Collective</p>
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Songs</th>
                        <th>Artist</th>
                        <th>Creator(s)</th>
                        <th>Difficulty</th>
                        <th>Diff Streng</th>
                        <th>Feeling</th>
                        <th>Forum</th>
                    </tr>
                    </thead>
                    <tbody>""");
        try {
            List<Level> levels = Level.getLevels();
            for(Level level : levels) {
                data.append("<tr><td>").append(level.id).append("</td><td>").append(level.song).append("</td><td>")
                        .append(level.artist).append("</td><td>").append(level.creator).append("</td>")
                        .append(getColored(level.diffString)).append("<td>").append(level.diffstrength).append("</td>")
                        .append(getColored(level.feelingString)).append(getColored(level.forumString)).append("</tr>");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "error\n" + e.getMessage();
        }
        return data.append("""
                    </tbody>
                </table>
                </body>
                </html>""").toString();
    }
    @GetMapping("/")
    public String asdf() {
        return allLevels();
    }
    public String getColored(String difficulty) {
        //if(difficulty == null) difficulty = "";
        String fixedDifficulty = difficulty.replaceAll("\\.", "_").replaceAll("\\+", "_");
        return "<td class=\"dif" + fixedDifficulty + "\">" + difficulty + "</td>";
    }
    @GetMapping("/new")
    public String newDesign() {
        StringBuilder data = new StringBuilder("""
                <!DOCTYPE html>
                <html>
                    <head>
                        <title>T21+C</title>
                        <link rel="shortcut icon" href="/t21c/t21c.ico">
                        <style type="text/css">
                            table {
                                border-collapse: collapse;
                                width: 100%;
                                max-width: 900px;
                                margin: 20px auto;
                            }

                            table td {
                                padding: 8px;
                                text-align: center;
                                font-size: 20px;
                            }
                            
                            table tr:hover {
                            background-color: #eeeeee;
                            cursor: pointer;
                        }

                            h1 {
                                text-align: center;
                            }

                            p {
                                text-align: center;
                                font-size: 25px;
                            }
                        
                        .dif {
                            vertical-align: middle;
                            padding-right: 10px;
                            width: 40px;
                            height: 40px;
                        }
                        </style>
                    </head>
                    <body>
                        <h1>The 21+ Collective</h1>
                        <p>ALL Levels</p>
                        <table>""");
        try {
            List<Level> levels = Level.getLevels();
            for(Level level : levels) {
                data.append("<tr><td><img src=\"https://github.com/T21C/T21C-assets/raw/main/difficons/").append(level.diffString)
                        .append(".png\" class=\"dif\"></td> <td>").append(level.artist).append(" - ").append(level.song)
                        .append(" | by ").append(level.creator).append("</td></tr>");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "error\n" + e.getMessage();
        }
        return data.append("""
                    </table>
                    </body>
                </html>""").toString();
    }
}
