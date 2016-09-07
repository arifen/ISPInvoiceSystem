package com.springapp.mvc.controller;

import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Slice;
import com.springapp.mvc.model.Customer;
import com.springapp.mvc.service.ChartService;
import com.springapp.mvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arifen on 8/30/16.
 */
@Controller
public class ChartController {

    @Autowired
    @Qualifier("chartService")
    ChartService chartService;

    @Autowired
    @Qualifier("customerService")
    CustomerService customerService;

    @RequestMapping(value = "/piechart", method = RequestMethod.GET)
    public String drawPieChart(ModelMap model) {
        System.out.println("come to the chartcontroller");
        Map<String, Long> dataMap = new HashMap<String, Long>();
        final List<Slice> slices = new ArrayList<Slice>();
        List<Customer> customerList = new ArrayList<Customer>();

        customerList = customerService.getAllCustomer();
        dataMap = chartService.makePieChart();
        System.out.println("come  size " + customerList.size() + "  map size " + dataMap.size());
        for (Map.Entry<String, Long> entry : dataMap.entrySet()) {
            System.out.println("come  size " + customerList.size() + " map value " + entry.getValue().intValue());
            int value = (entry.getValue().intValue() * 100) / customerList.size();
            System.out.println("val=== " + value);
            slices.add(Slice.newSlice(value, entry.getKey()));
            System.out.println("key " + entry.getKey() + "/ value  " + value);
        }
        /*chartService.makePieChart();
        for(int i=0; i<3;i++){
            slices.add(Slice.newSlice(25, "Mac"));
        }
        Slice s1 = Slice.newSlice(15,"Mac");
        Slice s2 = Slice.newSlice(50,"Window");
        Slice s3 = Slice.newSlice(25,"Linux");
        Slice s4 = Slice.newSlice(10, "Others");*/

        /*PieChart pieChart = GCharts.newPieChart(s1, s2, s3, s4);*/
        PieChart pieChart = GCharts.newPieChart(slices);
        pieChart.setTitle("Customer Chart", Color.BLACK, 15);
        pieChart.setSize(720, 360);
        pieChart.setThreeD(true);

        model.addAttribute("pieUrl", pieChart.toURLString());

        return "display";
    }
}
