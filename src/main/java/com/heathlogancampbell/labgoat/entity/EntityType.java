package com.heathlogancampbell.labgoat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.swing.text.html.parser.Entity;

@AllArgsConstructor
public enum EntityType
{
    BOX(Box.class),
    PLAYER(Player.class),
    PRESSURE_PLATE(PressurePlate.class);

    @Getter
    private Class<? extends EntityBase> clazz;
}
