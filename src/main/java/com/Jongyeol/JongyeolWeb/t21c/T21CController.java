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
                        .append(level.artist).append("</td><td>").append(level.creator).append("</td><td>")
                        .append(level.diff).append("</td><td>").append(level.diffstrength).append("</td><td>")
                        .append(level.feeling).append("</td><td>").append(level.forum).append("</td></tr>");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "error\n" + e.getMessage();
        }
        return data + """
                    </tbody>
                </table>
                </body>
                </html>
                """;
    }
}
