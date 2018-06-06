package io.github.salemlockwood.flyther.engine;

import io.github.salemlockwood.flyther.elements.Plane;
import io.github.salemlockwood.flyther.elements.Spikes;
import io.github.salemlockwood.flyther.utils.Screen;

public class CollisionVerifier {
    private final Plane plane;
    private final Spikes spikes;
    private Screen screen;

    public CollisionVerifier(Plane plane, Spikes spikes, Screen screen){
        this.plane = plane;
        this.spikes = spikes;
        this.screen = screen;
    }

    public boolean haveCrashed(){
        return spikes.hasCollisionWith(plane) || plane.getAltitude() > (screen.getHeight() - 70);
    }
}
