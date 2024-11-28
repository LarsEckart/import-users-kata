
package kata;

class NewRandomUserApiImporterTest extends RandomUserApiImporterTest {

    protected Importer getImporter(String url) {
        return new ModernRandomUserApiImporter(url);
    }
}
