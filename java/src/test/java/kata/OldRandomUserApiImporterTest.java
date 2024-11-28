package kata;

class OldRandomUserApiImporterTest extends RandomUserApiImporterTest {

    protected RandomUserApiImporter getImporter(String url) {
        return new RandomUserApiImporter(url);
    }
}
