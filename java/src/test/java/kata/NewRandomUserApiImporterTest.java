
package kata;

class NewRandomUserApiImporterTest extends RandomUserApiImporterTest {

    protected Importer getImporter() {
        return new ModernRandomUserApiImporter("https://randomuser.me/api/?inc=gender,name,email,location,dob&results=2&seed=a9b25cd955e2333h");
    }
}
