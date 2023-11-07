import java.util.HashMap;
import java.util.Objects;

/**
 * You are working for a new company trying to improve the package delivery
 * process "Algo-zon". The company wants to detect if a driver has taken a
 * circular root while delivering packages: at some point in the trip they
 * returned to somewhere they had been before.
 * 
 * The monitoring system currently in use at Algo-zon tracks routes taken by
 * drivers as a Linked List where each node has: id (integer) and a timestamp
 * (in unix format).
 * 
 * Each node represents a package pickup zone where a driver gets packages.
 * 
 * To check for inefficiency Algo-zon has tasked you with writing a function
 * that will detect whether a driver returned to the same pickup zone
 * 
 * Write a function that takes in the first node in the monitoring linked list
 * and returns the total time for the cycle or None if there isn't one.
 * 
 * You are given the code for a node
 * 
 * Code Author: <Hal Jones>
 */
public class AlgoZon {
	private static class PickupSnapshotNode {
		private final int id;
		private final int timestamp;
		private final PickupSnapshotNode next;

		public PickupSnapshotNode(int locationId, int timestamp, PickupSnapshotNode nextNode) {
			this.id = locationId;
			this.timestamp = timestamp;
			this.next = nextNode;
		}

		public int getId() {
			return this.id;
		}

		public int getTimestamp() {
			return this.timestamp;
		}

		public PickupSnapshotNode getNext() {
			return this.next;
		}
	}

	public static Integer detectCyclicRoute(PickupSnapshotNode start) {
		PickupSnapshotNode node4 = new PickupSnapshotNode(12345, 1685288860, null);
		PickupSnapshotNode node3 = new PickupSnapshotNode(21341, 1685288560, node4);
		PickupSnapshotNode node2 = new PickupSnapshotNode(12345, 1685288260, node3);
		PickupSnapshotNode node1 = new PickupSnapshotNode(32144, 1685287960, node2);

		HashMap<Integer, Integer> deliveryStops = new HashMap<>();

		PickupSnapshotNode node = start;
		Integer result = null;

		while (node != null) {
			if (!deliveryStops.containsKey(node.id)) {
				deliveryStops.put(node.id, node.timestamp);
			} else {
				result = node.timestamp - deliveryStops.get(node.id);
				System.out.printf("The cycle time is %s.\n", result);
			}

			node = node.next;
		}
		return result;
	}

	/*
	 * DO NOT EDIT BELOW THIS Below is the unit testing suite for this file. It
	 * provides all the tests that your code must pass to get full credit.
	 */
	private static void printTestResult(String testName, boolean result) {
		String color = result ? "\033[92m" : "\033[91m";
		String reset = "\033[0m";
		System.out.printf("%s[%b] %s%s\n", color, result, testName, reset);
	}

	private static void testAnswer(String testName, Integer expected, Integer actual) {
		if (Objects.equals(actual, expected)) {
			printTestResult(testName, true);
		} else {
			printTestResult(testName, false);
			System.out.printf("Expected: %s \nGot:      %s\n", expected, actual);
		}
	}

	public static void runTests() {
		testDetectsCyclicRide();
		testDetectsNonCyclicRide();
	}

	private static void testDetectsCyclicRide() {
		// Create nodes for a cyclic ride
		PickupSnapshotNode node4 = new PickupSnapshotNode(12345, 1685288860, null);
		PickupSnapshotNode node3 = new PickupSnapshotNode(21341, 1685288560, node4);
		PickupSnapshotNode node2 = new PickupSnapshotNode(12345, 1685288260, node3);
		PickupSnapshotNode node1 = new PickupSnapshotNode(32144, 1685287960, node2);

		// Call the detect_cyclic_ride function
		Integer result = detectCyclicRoute(node1);
		// Cycle is node 2 -> node 3 -> node 4
		Integer expected_answer = 1685288860 - 1685288260;
		testAnswer("testDetectsCyclicRide", expected_answer, result);
	}

	private static void testDetectsNonCyclicRide() {
		// Create nodes for a cyclic ride
		PickupSnapshotNode node4 = new PickupSnapshotNode(12345, 1685288860, null);
		PickupSnapshotNode node3 = new PickupSnapshotNode(21341, 1685288560, node4);
		PickupSnapshotNode node2 = new PickupSnapshotNode(32144, 1685288260, node3);
		PickupSnapshotNode node1 = new PickupSnapshotNode(12237, 1685287960, node2);

		// Call the detect_cyclic_ride function
		Integer result = detectCyclicRoute(node1);
		Integer expected_answer = null;
		testAnswer("testDetectsNonCyclicRide", expected_answer, result);
	}

	public static void main(String[] args) {
		AlgoZon.runTests();
	}
}