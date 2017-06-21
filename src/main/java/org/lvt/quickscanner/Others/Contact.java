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
    String Note;

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
