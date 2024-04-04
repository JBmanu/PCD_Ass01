package car;

import simengineseq.Percept;
import road.trafficLight.TrafficLightInfo;

import java.util.Optional;

/**
 * 
 * Percept for Car Agents
 * 
 * - position on the road
 * - nearest car, if present (distance)
 * - nearest semaphore, if presente (distance)
 * 
 */
public record CarPercept(double roadPos, Optional<CarAgentInfo> nearestCarInFront, Optional<TrafficLightInfo> nearestSem) implements Percept { }