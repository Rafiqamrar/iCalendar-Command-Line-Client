package eirb.pg203;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// public class OptionsTodo implements Options<Todo>{
//     public static List<Todo> filter(String type, List<Todo> todos){
//         List<Todo> res = new ArrayList<>();

//         switch (type) {

//             case "today" -> {
//                 LocalDate now = LocalDate.now();

//                 for (Todo t : todos) {
//                     String start = t.getStart();

//                     if (start != null) {
//                         LocalDate date = Utils.dateGetter(start);

//                         if (date.equals(now)) {
//                             res.add(event);
//                         }
//                     }
//                 }
//             }
//             case "tomorrow" -> {
//                 LocalDate tmrw = LocalDate.now().plusDays(1);

//                 for (Event event : events) {
//                     String start = event.get("DTSTAMP");

//                     if (start != null) {
//                         LocalDate date = Utils.dateGetter(start);

//                         if (date.equals(tmrw)) {
//                             res.add(event);
//                         }
//                     }
//                 }
//             }
//         }

//         return res;
//     }
// }
