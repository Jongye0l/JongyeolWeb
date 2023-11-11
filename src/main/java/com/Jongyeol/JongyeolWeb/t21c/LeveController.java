package com.Jongyeol.JongyeolWeb.t21c;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/t21c/level")
public class LeveController {
    @GetMapping("/{id}")
    public String level(@PathVariable("id") int id) {
        Level level = Level.getLevel(id);
        StringBuilder sb = new StringBuilder("""
                <html>
                <head>
                 <title>""" + level.artist + " - " + level.song + """
                 </title>
                 <link rel="shortcut icon" href="/t21c/t21c.ico">
                 <link rel="stylesheet" type="text/css" href="/t21c/level.css">
                </head>
                <body>
                 <div class="info">
                  <div class="items">
                   <div class="textitem artist">
                    <a href="/t21c">""" + level.artist + """
                </a>
                   </div>
                   <div class="textitem name">
                    <a href="/t21c">""" + level.song + """
                </a>
                   </div>
                   <div class="textitem creator">
                    <a href="/t21c">""" + level.creator + """
                </a>
                   </div>
                   <div class="levels">
                    <div class="level">
                     <div class="label">Level</div>
                     <div class="value">
                      <img src="https://github.com/T21C/T21C-assets/raw/main/difficons/""" + level.diffString + """
                .png" class="levelImage"/>
                     </div>
                    </div>
                    <div class="level">
                     <div class="label">Streng</div>
                     <div class="value">
                      <img src="https://github.com/T21C/T21C-assets/raw/main/difficons/""" + level.diffstrength + """
                .png" class="levelImage" style="padding-left: 6.57px"/>
                     </div>
                    </div>
                    <div class="level">
                     <div class="label">Felling</div>
                     <div class="value">
                      <img src="https://github.com/T21C/T21C-assets/raw/main/difficons/""" + level.feelingString + """
                .png" class="levelImage" style="padding-left: 6.57px"/>
                     </div>
                    </div>
                    <div class="level">
                     <div class="label">Forum</div>
                     <div class="value">
                      <img src="https://github.com/T21C/T21C-assets/raw/main/difficons/""" + level.forumString + """
                .png" class="levelImage" style="padding-left: 6.19px"/>
                     </div>
                    </div>
                   </div>
                   <div class="buttons">""");
        if(!level.vidLink.equals("")) {
            sb.append("    <a href=\"").append(level.vidLink).append("""
                    "target="_blank" rel="noreferrer" class="button">
                         <img src="/t21c/youtube.svg"/>
                        </a>""");
        }
        if(!level.dlLink.equals("")) {
            sb.append("    <a href=\"").append(level.dlLink).append(""" 
                    "target="_blank" rel="noreferrer" class="button">
                         <img src="/t21c/download.svg"/>
                        </a>""");
        }
        if(!level.workshopLink.equals("")) {
            sb.append("    <a href=\"").append(level.workshopLink).append("""
                    "target="_blank" rel="noreferrer" class="button">
                         <img src="/t21c/steam.svg"/>
                        </a>""");
        }
        sb.append("""            
                 </div>
                </div>
                <div class="video">
                 <iframe src="https://www.youtube.com/embed/""").append(level.ytId).append("""
                "title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; fullscreen;"></iframe>
                  </div>
                 </div>
                 <div class="leaderboard">
                  <div class="clears">
                   Clears
                   <div class="titles">
                    <div class="title">First Clear</div>
                    <div class="title">Highest X-Accuracy</div>
                    <div class="title">Best speed Trial</div>
                   </div>
                   <div class="topclears">
                    <a href="https://youtu.be/" target="_blank" rel="noreferrer" class="topclear first" style="color: white;">
                     <div class="top-player">Bwen</div>
                     <div class="top-description">2022. 1. 5</div>
                    </a>
                    <a href="https://youtu.be/" target="_blank" rel="noreferrer" class="topclear" style="color: white;">
                     <div class="top-player">Bwen</div>
                     <div class="top-description">99.99%</div>
                    </a>
                    <a href="https://youtu.be/" target="_blank" rel="noreferrer" class="topclear last" style="color: white;">
                     <div class="top-player">Bwen</div>
                     <div class="top-description">1x</div>
                    </a>
                   </div>
                  </div>
                  <div class="contents">
                   <a href="https://youtu.be/" target="_blank" rel="noreferrer" class="item">
                    <div class="rank">1</div>
                    <div class="content">
                     <div class="player">Bwen</div>
                     <div class="detail">
                      <div class="play">
                       <div class="score">
                        1234
                       </div>
                       <div class="playinfo">
                        <div style="min-width: 4em;">
                         <img src="/t21c/speed.svg" alt="Speed Trial: " style="height: 0.9em;">1x
                        </div>
                        <div>
                         <img src="/t21c/xaccuracy.svg" alt="X Accurancy: " style="height: 0.9em;">99.9%
                        </div>
                       </div>
                      </div>
                      <div class="time">
                       2022. 1. 5.
                      </div>
                     </div>
                    </div>
                   </a>
                  </div>
                 </div>
                </body>
                </html>""");
        return sb.toString();
    }
}
