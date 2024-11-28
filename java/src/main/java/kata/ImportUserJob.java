package kata;

import java.util.List;

class ImportUserJob {

    public List<Importer> importers;
    public List<Processor> processors;

    public ImportUserJob(List<Importer> importers1, List<Processor> processors1) {
        importers = importers1;
        processors = processors1;
    }

    public void run() {
        List<User> users =
                importers.stream()
                        .flatMap(i -> i.importUsers().stream())
                        .toList();

        processors.forEach(p -> p.process(users));
    }
}
