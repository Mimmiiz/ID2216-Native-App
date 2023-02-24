package com.example.nativeapplication;

import static com.example.nativeapplication.CalendarUtils.weekNumber;
import static com.example.nativeapplication.CalendarUtils.datesOfWeek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Booking extends AppCompatActivity {
    private TextView currentWeek;
    private TextView dateAndYear;
    private RecyclerView calendarRecyclerView;
    private TextView serviceNameView;
    private TextView serviceDescriptionView;
    private TextView servicePriceView;
    private TextView servicePhoneNumberView;
    private LinearLayout bookableTimesMon;
    private LinearLayout bookableTimesTue;
    private LinearLayout bookableTimesWed;
    private LinearLayout bookableTimesThu;
    private LinearLayout bookableTimesFri;
    private LinearLayout bookableTimesSat;
    private LinearLayout bookableTimesSun;
    private TextView dateViewMon;
    private TextView dateViewMonTue;
    private TextView dateViewMonWed;
    private TextView dateViewMonThu;
    private TextView dateViewMonFri;
    private TextView dateViewMonSat;
    private TextView dateViewMonSun;
    private RatingBar ratingView;
    private TextView ratingTextView;
    private TextView personNameView;
    private int weekNumber;
    private LocalDateTime minDate;
    private LocalDateTime maxDate = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
    private ArrayList<LocalDateTime> datesOfWeek = null;
    private String[][] bookableTimes = {
            {"10:00", "10:30", "14:00", "15:00"},
            {"10:00", "10:30", "11:00", "13:00", "13:30"},
            {"09:00", "10:30", "13:30", "14:00", "16:00", "20:00"},
            {"10:00", "10:30", "14:00", "14:30", "15:00", "19:30"},
            {"11:00", "11:30", "14:00", "15:00"},
            {"08:00", "09:30", "10:00", "12:30", "17:00"},
            {"10:00", "10:30", "11:00"}};

    private HashMap<String, Object> serviceDetails;
    private LocalDateTime selectedDateTime = null;
    private TextView selectedBookableTimeView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        initWidgets();

        String personName = getIntent().getStringExtra("personName");
        String personAvatar = getIntent().getStringExtra("personAvatar");
        String personPrice = getIntent().getStringExtra("personPrice");

        if (personName.equals("Joe Biden")) {
            serviceDetails = new HashMap<>();
            serviceDetails.put("name", "Joe Biden");
            serviceDetails.put("service", "Men's haircut");
            serviceDetails.put("description", "Includes hair wash and simple styling. Please note that the price may increase if more products or time is needed.");
            serviceDetails.put("price", personPrice);
            serviceDetails.put("phoneNumber", "+46000000000");
            serviceDetails.put("rating", (float) 4.4);
        }
        else if (personName.equals("Jane Smith")) {
            serviceDetails = new HashMap<>();
            serviceDetails.put("name", "Jane Smith");
            serviceDetails.put("service", "Womens's haircut");
            serviceDetails.put("description", "Includes hair wash and simple styling. Please note that the price may increase if more products or time is needed.");
            serviceDetails.put("price", personPrice);
            serviceDetails.put("phoneNumber", "+46111111111");
            serviceDetails.put("rating", (float) 3.8);
        }
        else {
            serviceDetails = new HashMap<>();
            serviceDetails.put("name", "Bob Johnson");
            serviceDetails.put("service", "Beard shaving and trimming");
            serviceDetails.put("description", "Beard grooming for all types of beards and styles. Price may increase if more products or time is needed.");
            serviceDetails.put("price", personPrice);
            serviceDetails.put("phoneNumber", "+46111111111");
            serviceDetails.put("rating", (float) 4.1);
        }

        minDate = LocalDateTime.now();
        weekNumber = weekNumber(minDate);
        datesOfWeek = datesOfWeek(minDate);
        Log.d("Dates of week", datesOfWeek.toString());

        showWeek();
        showDateAndYear();
        showDatesOfWeek();
        showBookableTimes();
        showServiceDetails();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        /* Redirects to order confirmation, needs to be changed to redirect to the checkout page */
        Button bookButton = findViewById(R.id.button_book);
        bookButton.setOnClickListener(v -> {
            Intent i = new Intent(Booking.this, OrderConfirmation.class);
            i.putExtra("name", serviceDetails.get("name").toString());
            i.putExtra("service", serviceDetails.get("service").toString());
            i.putExtra("price", serviceDetails.get("price").toString());
            i.putExtra("phoneNumber", serviceDetails.get("phoneNumber").toString());
            i.putExtra("bookedDate", selectedDateTime.format(dateFormatter));
            i.putExtra("bookedTime", selectedDateTime.format(timeFormatter));
            startActivity(i);
        });
    }

    private void initWidgets() {
        currentWeek = findViewById(R.id.booking_week_number);
        dateAndYear = findViewById(R.id.booking_start_end_dates);
        serviceNameView = findViewById(R.id.booking_service_name_text);
        serviceDescriptionView = findViewById(R.id.booking_service_description_text);
        servicePriceView = findViewById(R.id.booking_service_price_text);
        servicePhoneNumberView = findViewById(R.id.booking_service_telephone_text);
        ratingView = findViewById(R.id.booking_rating_bar);
        ratingTextView = findViewById(R.id.booking_rating_text);
        personNameView = findViewById(R.id.booking_person_name);
        bookableTimesMon = findViewById(R.id.bookable_times_mon);
        bookableTimesTue = findViewById(R.id.bookable_times_tue);
        bookableTimesWed = findViewById(R.id.bookable_times_wed);
        bookableTimesThu = findViewById(R.id.bookable_times_thu);
        bookableTimesFri = findViewById(R.id.bookable_times_fri);
        bookableTimesSat = findViewById(R.id.bookable_times_sat);
        bookableTimesSun = findViewById(R.id.bookable_times_sun);
        dateViewMon = findViewById(R.id.date_text_mon);
        dateViewMonTue = findViewById(R.id.date_text_tue);
        dateViewMonWed = findViewById(R.id.date_text_wed);
        dateViewMonThu = findViewById(R.id.date_text_thu);
        dateViewMonFri = findViewById(R.id.date_text_fri);
        dateViewMonSat = findViewById(R.id.date_text_sat);
        dateViewMonSun = findViewById(R.id.date_text_sun);
    }
    private void showWeek() {
        String week = "Week " + weekNumber;
        currentWeek.setText(week);
    }

    @SuppressLint("SetTextI18n")
    private void showDateAndYear() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");
        int firstDay = datesOfWeek.get(0).getDayOfMonth();
        int lastDay = datesOfWeek.get(6).getDayOfMonth();
        String firstDayMonth = datesOfWeek.get(0).format(formatter);
        String lastDayMonth = datesOfWeek.get(6).format(formatter);

        /* Handles the case when the year is different between the first day and last day of the week */
        String year;
        if (datesOfWeek.get(0).getYear() != datesOfWeek.get(6).getYear()) {
            year = datesOfWeek.get(0).getYear() + "/" + datesOfWeek.get(6).getYear();
        } else {
            year = "" + datesOfWeek.get(0).getYear();
        }

        String dateYear = firstDay + " " + firstDayMonth + " - " + lastDay + " " + lastDayMonth + " " + year;

        dateAndYear.setText(dateYear);
    }

    private void showServiceDetails() {
        personNameView.setText(serviceDetails.get("name").toString());
        ratingTextView.setText(serviceDetails.get("rating").toString());
        ratingView.setRating((Float)serviceDetails.get("rating"));
        serviceNameView.setText(serviceDetails.get("service").toString());

        String price = "Price: " + serviceDetails.get("price").toString();
        servicePriceView.setText(price);

        serviceDescriptionView.setText(serviceDetails.get("description").toString());

        String phoneNumber = "Phone number: " + serviceDetails.get("phoneNumber").toString();
        servicePhoneNumberView.setText(phoneNumber);

    }

    private void showBookableTimes() {
        bookableTimesMon.removeAllViews();
        bookableTimesTue.removeAllViews();
        bookableTimesWed.removeAllViews();
        bookableTimesThu.removeAllViews();
        bookableTimesFri.removeAllViews();
        bookableTimesSat.removeAllViews();
        bookableTimesSun.removeAllViews();

        addBookableTimes(bookableTimesMon, bookableTimes[0], datesOfWeek.get(0));
        addBookableTimes(bookableTimesTue, bookableTimes[1], datesOfWeek.get(1));
        addBookableTimes(bookableTimesWed, bookableTimes[2], datesOfWeek.get(2));
        addBookableTimes(bookableTimesThu, bookableTimes[3], datesOfWeek.get(3));
        addBookableTimes(bookableTimesFri, bookableTimes[4], datesOfWeek.get(4));
        addBookableTimes(bookableTimesSat, bookableTimes[5], datesOfWeek.get(5));
        addBookableTimes(bookableTimesSun, bookableTimes[6], datesOfWeek.get(6));
    }

    private void addBookableTimes(ViewGroup parent, String[] bookableTimes, LocalDateTime date) {
        LayoutInflater inflater = getLayoutInflater();

        for (int i = 0; i < bookableTimes.length; i++) {
            LocalDateTime dateTime = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), Integer.parseInt(bookableTimes[i].substring(0, 2)), Integer.parseInt(bookableTimes[i].substring(3, 5)));
            if (minDate.isBefore(dateTime)) {
                View bookableTimeCell = inflater.inflate(R.layout.bookable_times_cell, parent, false);
                TextView textView = (TextView) bookableTimeCell.findViewById(R.id.bookable_time_cell);
                textView.setText(bookableTimes[i]);
                textView.setTag(dateTime);
                parent.addView(bookableTimeCell);
            }
        }
    }

    private void showDatesOfWeek() {
        String dateMon = "" + datesOfWeek.get(0).getDayOfMonth();
        String dateTue = "" + datesOfWeek.get(1).getDayOfMonth();
        String dateWed = "" + datesOfWeek.get(2).getDayOfMonth();
        String dateThu = "" + datesOfWeek.get(3).getDayOfMonth();
        String dateFri = "" + datesOfWeek.get(4).getDayOfMonth();
        String dateSat = "" + datesOfWeek.get(5).getDayOfMonth();
        String dateSun = "" + datesOfWeek.get(6).getDayOfMonth();

        dateViewMon.setText(dateMon);
        dateViewMonTue.setText(dateTue);
        dateViewMonWed.setText(dateWed);
        dateViewMonThu.setText(dateThu);
        dateViewMonFri.setText(dateFri);
        dateViewMonSat.setText(dateSat);
        dateViewMonSun.setText(dateSun);
   }

    /* Set the previous week */
    public void previousWeek(View view) {
        datesOfWeek = datesOfWeek(datesOfWeek.get(0).minusWeeks(1));
        weekNumber = weekNumber(datesOfWeek.get(0));
        showWeek();
        showDateAndYear();
        showDatesOfWeek();
        showBookableTimes();
        //disableButton();
    }

    /* Set the next week */
    public void nextWeek(View view) {
        datesOfWeek = datesOfWeek(datesOfWeek.get(0).plusWeeks(1));
        weekNumber = weekNumber(datesOfWeek.get(0));
        showWeek();
        showDateAndYear();
        showDatesOfWeek();
        showBookableTimes();
        //disableButton();
    }

    private void disableButton() {
        ImageButton prevButton = (ImageButton) findViewById(R.id.button_prev_week);
        if (minDate.isAfter(datesOfWeek.get(0))) {
            prevButton.setEnabled(false);
        } else {
            prevButton.setEnabled(true);
        }

        if (maxDate.isAfter(datesOfWeek.get(0))) {
            ImageButton button = (ImageButton) findViewById(R.id.button_next_week);
            button.setEnabled(false);
        } else {
            prevButton.setEnabled(true);
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void selectDate(View view) {
        if (selectedBookableTimeView != null) {
            selectedBookableTimeView.setBackground(getResources().getDrawable(R.drawable.border_bookable_times));
        }

        TextView textView = (TextView) view;
        selectedDateTime = (LocalDateTime)textView.getTag();
        selectedBookableTimeView = textView;
        textView.setBackground(getResources().getDrawable(R.drawable.background_bookable_times));
        Log.d("Selected date", selectedDateTime.toString());
    }
}