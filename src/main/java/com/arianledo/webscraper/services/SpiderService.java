package com.arianledo.webscraper.services;

import com.arianledo.webscraper.repositories.WebpageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

@Service
public class SpiderService {

    @Autowired
    private WebpageService webpageService;

    @Autowired
    private WebpageRepository webpageRepository;

    public void star(String initialUrl) {
        int stop = 0;
        scrapRecursive(initialUrl, stop);
//
//        List<Integer> pila = new ArrayList<>();
//
//        int cycle = 0;
//        recursivefunction(pila, cycle);
//
//        return 1;

    }

//    public static int[] generateArray() {
//        int[] array = new int[10];
//        Random random = new Random();
//
//        for (int i = 0; i < 10; i++) {
//            array[i] = random.nextInt(10)+1; // Generate random integers between 0 and 99
//        }
//        return array;
//    }
//
//    public void recursivefunction(List<Integer> pila, int cycle) {
//        cycle++;
//        System.out.println("pila: " + pila);
//        int[] array = generateArray();
//        System.out.println("array: " + Arrays.toString(array));
//
//        for (int i = 0; i < array.length; i++) {
//
//            System.out.println("Cycle: " + cycle);
//            if (!pila.contains(array[i])) {
//                pila.add(array[i]);
//                System.out.println("add: " + array[i]);
//                recursivefunction(pila, cycle);
//            } else {
//                System.out.println("already in the list: " + array[i]);
//            }
//        }
//    }

    public void scrapRecursive(String initialUrl, int stop) {

        System.out.println("Cycle: " + stop);
        webpageService.scrapAndSave(initialUrl);
        System.out.println("Saving: " + initialUrl);

        stop++;
        if (stop <= 5) {
            List<String> links = webpageService.getAllLinksFromWebpage(initialUrl);
            System.out.println("Links: " + links);

            int finalStop = stop;
            links.forEach(link -> {
                if (webpageRepository.findByUrl(link) == null) {
                    scrapRecursive(link, finalStop);
//                    if(stop.get() <= 5) {
//                        scrapRecursive(link, stop);
//                    } else {
//                        System.out.println("Stopped: " + stop.get());
//                    }
                } else {
                    System.out.println("Already saved");
                }
            });
        }


    }
}
