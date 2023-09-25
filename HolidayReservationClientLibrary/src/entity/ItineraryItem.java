/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jayso
 */
public class ItineraryItem implements Serializable {

//  private Long itineraryItemId;
    private Integer sequenceNumber;
    private Date dateTime;
    private String activity;

    public ItineraryItem() {
    }

    public ItineraryItem(Integer sequenceNumber, Date dateTime, String activity) {
//      this.itineraryItemId = itineraryItemId;
        this.sequenceNumber = sequenceNumber;
        this.dateTime = dateTime;
        this.activity = activity;
    }

//    public Long getItineraryItemId() {
//        return itineraryItemId;
//    }
//
//    public void setItineraryItemId(Long itineraryItemId) {
//        this.itineraryItemId = itineraryItemId;
//    }
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        String activityToString = this.activity;
        int endIndex = activityToString.indexOf(" at ");
        if (endIndex != -1) {
            activityToString = activityToString.substring(0, endIndex + 3);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mma");
        String formattedDate = dateFormat.format(this.dateTime);

        return String.format("%d %s %s", this.sequenceNumber, formattedDate, activityToString);
    }

}
