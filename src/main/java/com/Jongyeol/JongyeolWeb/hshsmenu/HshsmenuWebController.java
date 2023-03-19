package com.Jongyeol.JongyeolWeb.hshsmenu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;

@RestController
public class HshsmenuWebController {
    private final String noMenu = "<Main>" +
            "   <br>" +
            "   <br>" +
            "   <br>식단이 없습니다." +
            "</Main>";

    @GetMapping("/hshsmenu")
    public String index() {
        System.out.println("new web connection");
        Day day = new Day();
        return "<!DOCTYPE html>" +
                "<html lang=\"kr\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<title>형석고 급식 알리미</title>" +
                "</head>" +
                "<frameset rows=\"100%,*\" border=\"0\">" +
                "<frame src=\"/hshsmenu/day/" + day.getToday() + "\"></frameset>" +
                "</html>";
    }
    /*
    @GetMapping("/teststop")
    public String stop() {
        System.exit(0);
        return "";
    }
     */
    /*
       @GetMapping("/")
       public String index(HttpServletRequest httpServletRequest) throws ParseException, IOException {
           System.out.println("new web connection");
           return day(Integer.parseInt(new Day().getToday()), httpServletRequest);
       }
   */
    @GetMapping("/hshsmenu/day/{day}")
    public String day(@PathVariable("day") int day, HttpServletRequest httpServletRequest) throws ParseException, IOException {
        Day day1 = new Day(day);
        Document doc = Jsoup.connect("https://school.cbe.go.kr/hshs-h/M01030803/list?ymd=" + day1.getToday()).get();
        Elements contents = doc.select("ul.tch-lnc-list");
        Elements lunch = null, dinner = null;
        for(Element el : contents.select("li.tch-lnc-wrap")) {
            if (el.select("dt").text().equals("중식")) lunch = el.select("dd.tch-lnc").select("ul");
            if (el.select("dt").text().equals("석식")) dinner = el.select("dd.tch-lnc").select("ul");
        }
        StringBuilder html = new StringBuilder("<!DOCTYPE html>" +
                "<html lang=\"kr\">" +
                "<head>" +
                "   <meta charset=\"UTF-8\">" +
                "   <title>형석고 급식 알리미</title>" +
                "   <link rel=\"stylesheet\" type=\"text/css\" href=\"/hshsmenu/");
        if(DeviceUtils.getCurrentDevice(httpServletRequest).isMobile() || DeviceUtils.getCurrentDevice(httpServletRequest).isTablet()) {
            html.append("mobile.css");
        } else {
            html.append("pc.css");
        }
        html.append("\">" +
                "   <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "</head>" +
                "<body>" +
                "   <box>" +
                "       <div class=\"box lunch\">" +
                "           <Main>중식</Main>");
        if(lunch == null) {
            html.append(noMenu);
        } else {
            String lunchText = "<menu>" +
                    "   <br>" ;
            for(Element el : lunch.select("li")) {
                lunchText += el.text() + "<br>";
            }
            lunchText += "</menu>";
            lunchText = lunchText.replace("*", "");
            lunchText = lunchText.replace(".", "");
            for (int i = 0; i < 10; i++) {
                lunchText = lunchText.replace("" + i, "");
            }
            lunchText = lunchText.replace("()", "");
            html.append(lunchText);
        }
        html.append("</div>" + "<div class=\"box dinner\">" + "<Main>석식</Main>");
        if(dinner == null) {
            html.append(noMenu);
        } else {
            String dinnerText = "<menu>" +
                    "   <br>" ;
            for(Element el : dinner.select("li")) {
                dinnerText += el.text() + "<br>";
            }
            dinnerText += "</menu>";
            dinnerText = dinnerText.replace("*", "");
            dinnerText = dinnerText.replace(".", "");
            for (int i = 0; i < 10; i++) {
                dinnerText = dinnerText.replace("" + i, "");
            }
            dinnerText = dinnerText.replace("()", "");
            html.append(dinnerText);
        }
        return html.append("       </div>" +
                "   </box>" +
                "</body>" +
                "<header>" +
                "   <a href=\"/hshsmenu/day/" + day1.getPrevious() + "\" class=\"button pre\" style=\"float: left\">어제</a>" +
                "   <a href=\"/hshsmenu/day/" + day1.getNext() + "\" class=\"button nex\" style=\"float: right\">내일</a>" +
                "   <div class=\"practicel\">" + day1.getTitle() + "</div>" +
                "</header></html>").toString();
    }

    @GetMapping("/hshsmenu-privacy")
    public String getMenuPrivacy() {
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset='utf-8'>" +
                "<meta name='viewport' content='width=device-width'>" +
                "<title>Privacy Policy</title>" +
                "<style> body { font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; padding:1em; } </style>" +
                "</head>" +
                "<body>" +
                "<strong>Privacy Policy</strong> <p>" +
                "Jongyeol built the 형석고 급식 알리미 app as" +
                "a Free app. This SERVICE is provided by" +
                "Jongyeol at no cost and is intended for use as" +
                "is." +
                "</p><p>" +
                "This page is used to inform visitors regarding my" +
                "policies with the collection, use, and disclosure of Personal" +
                "Information if anyone decided to use my Service." +
                "</p><p>" +
                "                  If you choose to use my Service, then you agree to" +
                "                  the collection and use of information in relation to this" +
                "                  policy. The Personal Information that I collect is" +
                "                  used for providing and improving the Service. I will not use or share your information with" +
                "                  anyone except as described in this Privacy Policy." +
                "                </p> <p>" +
                "                  The terms used in this Privacy Policy have the same meanings" +
                "                  as in our Terms and Conditions, which are accessible at" +
                "                  형석고 급식 알리미 unless otherwise defined in this Privacy Policy." +
                "                </p> <p><strong>Information Collection and Use</strong></p> <p>" +
                "                  For a better experience, while using our Service, I" +
                "                  may require you to provide us with certain personally" +
                "                  identifiable information. The information that" +
                "                  I request will be retained on your device and is not collected by me in any way." +
                "                </p> <div><p>" +
                "                    The app does use third-party services that may collect" +
                "                    information used to identify you." +
                "                  </p> <p>" +
                "                    Link to the privacy policy of third-party service providers used" +
                "                    by the app" +
                "                  </p> <ul><li><a href=\"https://www.google.com/policies/privacy/\" target=\"_blank\" rel=\"noopener noreferrer\">Google Play Services</a></li><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----></ul></div> <p><strong>Log Data</strong></p> <p>" +
                "                  I want to inform you that whenever you" +
                "                  use my Service, in a case of an error in the app" +
                "                  I collect data and information (through third-party" +
                "                  products) on your phone called Log Data. This Log Data may" +
                "                  include information such as your device Internet Protocol" +
                "                  (“IP”) address, device name, operating system version, the" +
                "                  configuration of the app when utilizing my Service," +
                "                  the time and date of your use of the Service, and other" +
                "                  statistics." +
                "                </p> <p><strong>Cookies</strong></p> <p>" +
                "                  Cookies are files with a small amount of data that are" +
                "                  commonly used as anonymous unique identifiers. These are sent" +
                "                  to your browser from the websites that you visit and are" +
                "                  stored on your device's internal memory." +
                "                </p> <p>" +
                "                  This Service does not use these “cookies” explicitly. However," +
                "                  the app may use third-party code and libraries that use" +
                "                  “cookies” to collect information and improve their services." +
                "                  You have the option to either accept or refuse these cookies" +
                "                  and know when a cookie is being sent to your device. If you" +
                "                  choose to refuse our cookies, you may not be able to use some" +
                "                  portions of this Service." +
                "                </p> <p><strong>Service Providers</strong></p> <p>" +
                "                  I may employ third-party companies and" +
                "                  individuals due to the following reasons:" +
                "                </p> <ul><li>To facilitate our Service;</li> <li>To provide the Service on our behalf;</li> <li>To perform Service-related services; or</li> <li>To assist us in analyzing how our Service is used.</li></ul> <p>" +
                "                  I want to inform users of this Service" +
                "                  that these third parties have access to their Personal" +
                "                  Information. The reason is to perform the tasks assigned to" +
                "                  them on our behalf. However, they are obligated not to" +
                "                  disclose or use the information for any other purpose." +
                "                </p> <p><strong>Security</strong></p> <p>" +
                "                  I value your trust in providing us your" +
                "                  Personal Information, thus we are striving to use commercially" +
                "                  acceptable means of protecting it. But remember that no method" +
                "                  of transmission over the internet, or method of electronic" +
                "                  storage is 100% secure and reliable, and I cannot" +
                "                  guarantee its absolute security." +
                "                </p> <p><strong>Links to Other Sites</strong></p> <p>" +
                "                  This Service may contain links to other sites. If you click on" +
                "                  a third-party link, you will be directed to that site. Note" +
                "                  that these external sites are not operated by me." +
                "                  Therefore, I strongly advise you to review the" +
                "                  Privacy Policy of these websites. I have" +
                "                  no control over and assume no responsibility for the content," +
                "                  privacy policies, or practices of any third-party sites or" +
                "                  services." +
                "                </p> <p><strong>Children’s Privacy</strong></p> <div><p>" +
                "                    These Services do not address anyone under the age of 13." +
                "                    I do not knowingly collect personally" +
                "                    identifiable information from children under 13 years of age. In the case" +
                "                    I discover that a child under 13 has provided" +
                "                    me with personal information, I immediately" +
                "                    delete this from our servers. If you are a parent or guardian" +
                "                    and you are aware that your child has provided us with" +
                "                    personal information, please contact me so that" +
                "                    I will be able to do the necessary actions." +
                "                  </p></div> <!----> <p><strong>Changes to This Privacy Policy</strong></p> <p>" +
                "                  I may update our Privacy Policy from" +
                "                  time to time. Thus, you are advised to review this page" +
                "                  periodically for any changes. I will" +
                "                  notify you of any changes by posting the new Privacy Policy on" +
                "this page." +
                "</p><p>This policy is effective as of 2022-03-28</p> <p><strong>Contact Us</strong></p> <p>" +
                "If you have any questions or suggestions about my" +
                "Privacy Policy, do not hesitate to contact me at bcpjy1233@gmail.com." +
                "</p><p>This privacy policy page was created at <a href=\"https://privacypolicytemplate.net\" target=\"_blank\" rel=\"noopener noreferrer\">privacypolicytemplate.net </a>and modified/generated by <a href=\"https://app-privacy-policy-generator.nisrulz.com/\" target=\"_blank\" rel=\"noopener noreferrer\">App Privacy Policy Generator</a></p>" +
                "</body>" +
                "</html>";
    }
}
