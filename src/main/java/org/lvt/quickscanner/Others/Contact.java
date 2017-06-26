package org.lvt.quickscanner.Others;

/**
 * Created by ylt1hc on 6/19/2017.
 */
public class Contact {

    String Type;
    String Name;
    String Org;
    String Tel;
    String Url;
    String Email;
    String Adr;

    public Contact(String recode){
        parseContact(recode);
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOrg() {
        return Org;
    }

    public void setOrg(String org) {
        Org = org;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAdr() {
        return Adr;
    }

    public void setAdr(String adr) {
        Adr = adr;
    }

    String Note;



    public static boolean isContact(String string){
        return string.contains("BEGIN:")&&string.contains("N:");
    }

    public Contact parseContact(String contact){
        String[] tokens = contact.split("\n");

        for (int i = 0; i < tokens.length; i++)
        {
            System.out.println(" "+tokens[i]);

            if(tokens[i].startsWith("BEGIN:"))
            {
                Type= tokens[i].substring(6);
            }
            else if(tokens[i].startsWith("N:"))
            {
                Name= tokens[i].substring(2);
            }
            else if(tokens[i].startsWith("ORG:"))
            {
                Org= tokens[i].substring(4);
            }
            else if(tokens[i].startsWith("TEL:"))
            {
                Tel= tokens[i].substring(4);
            }
            else if(tokens[i].startsWith("URL:"))
            {
                Url= tokens[i].substring(4);
            }
            else if(tokens[i].startsWith("EMAIL:"))
            {
                Email= tokens[i].substring(6);
            }
            else if(tokens[i].startsWith("ADR:"))
            {
                Adr= tokens[i].substring(4);
            }
            else if(tokens[i].startsWith("NOTE:"))
            {
                Note= tokens[i].substring(5);
            }
        }
        return this;
    }

}
