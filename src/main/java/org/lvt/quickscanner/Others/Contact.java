package org.lvt.quickscanner.Others;

import java.util.List;

/**
 * Created by ylt1hc on 6/19/2017.
 */
public class Contact {

    String Type;
    String Name;
    String Org;
    String     Title;
    List<Item> Tel;
    String     Url;
    List<Item> Email;
    List<Item> Adr;
    String     Note;

    public Contact(String recode){
        parseContact(recode);
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setTel(List<Item> tel) {
        Tel = tel;
    }

    public void setEmail(List<Item> email) {
        Email = email;
    }

    public void setAdr(List<Item> adr) {
        Adr = adr;
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


    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }


    class Item{
        String type;
        String value;
        public Item(String type, String value){
            this.type = type;
            this.value = value;
        }
    }

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
//                Tel= tokens[i].substring(4);
            }
            else if(tokens[i].startsWith("URL:"))
            {
                Url= tokens[i].substring(4);
            }
            else if(tokens[i].startsWith("EMAIL:"))
            {
//                Email= tokens[i].substring(6);
            }
            else if(tokens[i].startsWith("ADR:"))
            {
//                Adr= tokens[i].substring(4);
            }
            else if(tokens[i].startsWith("NOTE:"))
            {
                Note= tokens[i].substring(5);
            }
        }
        return this;
    }

}
