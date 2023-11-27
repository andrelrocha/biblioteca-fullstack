package rocha.andre.project.domain.user.UseCase;

import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.user.User;
import rocha.andre.project.domain.user.UserRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class CSVExporterUser {
    @Autowired
    private UserRepository userRepository;

    public void exportUsersToCSV() {
        var csvFilePath = "src/main/resources/csv/users.csv";

        try (FileWriter writer = new FileWriter(csvFilePath);
             CSVWriter csvWriter = new CSVWriter(writer,
                     CSVWriter.DEFAULT_SEPARATOR,
                     CSVWriter.NO_QUOTE_CHARACTER,
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                     CSVWriter.DEFAULT_LINE_END)) {

            String[] headerRecord = {"ID", "Login", "Matricula", "Tipo"};
            csvWriter.writeNext(headerRecord);

            List<User> users = userRepository.findAll();

            for (User user : users) {
                String[] userData = {
                        String.valueOf(user.getId()),
                        user.getLogin(),
                        String.valueOf(user.getMatricula()),
                        user.getTipo().toString()
                };
                csvWriter.writeNext(userData);
            }

            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.toString());
        }
    }
}
