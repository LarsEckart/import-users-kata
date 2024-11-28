package kata;

class OldRandomUserApiImporterTest extends RandomUserApiImporterTest {

    protected RandomUserApiImporter getImporter() {
        return new RandomUserApiImporter("https://randomuser.me/api/?inc=gender,name,email,location,dob&results=2&seed=a9b25cd955e2333h");
    }
}
