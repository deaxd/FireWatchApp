package foi.hr.firewatchapp.helper;

import hr.foi.air.database.database.entities.Fireman;
import hr.foi.air.database.database.entities.Intervention;
import hr.foi.air.database.database.entities.Organization;

/**
 * Created by Denis on 18.11.2016..
 */

public class MockData {

        public static void writeAll(){

            Organization organization1 = new Organization();
            Organization organization2 = new Organization();
            Fireman clan1 = new Fireman();
            Fireman clan2 = new Fireman();
            Fireman clan3 = new Fireman();

            organization1.setName("Dobrovoljno društvo Gornji Hrašćan");
            organization1.setShortName("DVD Gornji Hrašćan");
            organization1.setAddress("Čakovecča 33");
            organization1.save();

            organization2.setName("Javna vatrogasna postrojba Čakovec");
            organization2.setShortName("JVP Čakovec");
            organization2.setAddress("Čakovečka 33");
            organization2.save();


            clan1.setName("Ivan");
            clan1.setSurname("Ivić");
            clan1.setOib("1234567891234");
            clan1.setOrganization(organization1);
            clan1.setUsername("iivic");
            clan1.setPassword("Lozinka123");
            clan1.setLieutenant(true);
            clan1.save();

            clan2.setName("Marko");
            clan2.setSurname("Markić");
            clan2.setOib("2548742221158");
            clan2.setOrganization(organization1);
            clan2.setUsername("mmarkic");
            clan2.setPassword("password");
            clan2.setLieutenant(true);
            clan2.save();

            clan3.setName("Ivana");
            clan3.setSurname("Ivanić");
            clan3.setOib("2548742277158");
            clan3.setOrganization(organization2);
            clan3.setUsername("iivanic");
            clan3.setPassword("password1");
            clan3.setLieutenant(true);
            clan3.save();


            Intervention intervention1  = new Intervention();
            intervention1.setKindOfIntervention("Požar krovišta");
            intervention1.setAddress("Čakovečka 3");
            //intervention1.setInitialTIme("12:14");
            intervention1.setDuration(1);
            intervention1.setDescription("Požar na gredi uz dimnjak");
            intervention1.save();

        }
}
