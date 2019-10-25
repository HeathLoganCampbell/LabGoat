package com.heathlogancampbell.labgoat.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Location implements Cloneable
{
    private double x, y;

    public void addVelocity(Velocity velocity)
    {
        this.x += velocity.getX();
        this.y += velocity.getY();
    }

    public Location clone()
    {
        return new Location(x, y);
    }
}
