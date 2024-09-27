package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception{

        if (customerRepository.count() == 1) {

            Division d = divisionRepository.findById(12L).orElse(null);


            Customer brian = new Customer("Brian", "Ndlovu",
                    "15  Ridge Drive", "32854",
                    "0773569012", d);
            customerRepository.save(brian);

            Customer mkhululi = new Customer("Mkhululi", "Ndabambi",
                    "1204 Emakhandeni", "45123", "3690125899", d);
            customerRepository.save(mkhululi);

            Customer bekithemba = new Customer("Bekithemba", "Hadebe",
                    "3654 Malapala Road", "32895", "07729637894", d);
            customerRepository.save(bekithemba);

            Customer panyu = new Customer("Panyu", "Ndlovu",
                    "8588 Tshiyakwakhiwe Road", "37859", "08129663589", d);
            customerRepository.save(panyu);

            Customer sicelo = new Customer("Sicelo", "Ndlovu",
                    "Sibona Primary School", "89564", "2569585691", d);
            customerRepository.save(sicelo);
        }

    }
}