package com.example.algoviz.service;

import com.example.algoviz.model.AlgorithmInfo;
import com.example.algoviz.model.AlgorithmResult;
import com.example.algoviz.model.CodeLine;
import com.example.algoviz.model.ExecutionStep;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlgorithmService {
    private final List<AlgorithmInfo> algorithms;

    public AlgorithmService() {
        algorithms = new ArrayList<>();

        List<String> allLangs = List.of("Java", "Python", "JavaScript", "C++", "C#", "TypeScript");

        AlgorithmInfo ai;
        Map<String,String> codes;

        ai = new AlgorithmInfo(
            "bubble",
            "Bubble Sort",
            "Sort an array by repeatedly swapping adjacent out-of-order elements.",
            "Java",
            "Sorting",
            List.of(5, 3, 8, 4, 2),
            "// Java - Bubble Sort\nvoid bubbleSort(int[] arr) {\n    int n = arr.length;\n    for (int i = 0; i < n - 1; i++) {\n        for (int j = 0; j < n - i - 1; j++) {\n            if (arr[j] > arr[j + 1]) {\n                int temp = arr[j];\n                arr[j] = arr[j + 1];\n                arr[j + 1] = temp;\n            }\n        }\n    }\n}",
            false
        );
        codes = new HashMap<>();
        codes.put("Java", ai.getCode());
        codes.put("Python", "# Python - Bubble Sort\ndef bubble_sort(arr):\n    n = len(arr)\n    for i in range(n-1):\n        for j in range(n-i-1):\n            if arr[j] > arr[j+1]:\n                arr[j], arr[j+1] = arr[j+1], arr[j]\n");
        codes.put("JavaScript", "// JavaScript - Bubble Sort\nfunction bubbleSort(arr) {\n    const n = arr.length;\n    for (let i = 0; i < n - 1; i++) {\n        for (let j = 0; j < n - i - 1; j++) {\n            if (arr[j] > arr[j + 1]) {\n                const temp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = temp;\n            }\n        }\n    }\n}\n");
        codes.put("C++", "// C++ - Bubble Sort\nvoid bubbleSort(vector<int>& arr) {\n    int n = arr.size();\n    for (int i = 0; i < n - 1; ++i) {\n        for (int j = 0; j < n - i - 1; ++j) {\n            if (arr[j] > arr[j + 1]) swap(arr[j], arr[j + 1]);\n        }\n    }\n}\n");
        codes.put("C#", "// C# - Bubble Sort\nvoid BubbleSort(int[] arr) {\n    int n = arr.Length;\n    for (int i = 0; i < n - 1; i++) {\n        for (int j = 0; j < n - i - 1; j++) {\n            if (arr[j] > arr[j + 1]) {\n                int temp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = temp;\n            }\n        }\n    }\n}\n");
        codes.put("TypeScript", "// TypeScript - Bubble Sort\nfunction bubbleSort(arr: number[]): void {\n    const n = arr.length;\n    for (let i = 0; i < n - 1; i++) {\n        for (let j = 0; j < n - i - 1; j++) {\n            if (arr[j] > arr[j + 1]) {\n                [arr[j], arr[j + 1]] = [arr[j + 1], arr[j]];\n            }\n        }\n    }\n}\n");
        ai.setCodes(codes);
        algorithms.add(ai);

        ai = new AlgorithmInfo(
            "selection",
            "Selection Sort",
            "Choose the smallest remaining item and move it into the sorted prefix.",
            "Java",
            "Sorting",
            List.of(7, 4, 5, 2, 6),
            "// Java - Selection Sort\nvoid selectionSort(int[] arr) {\n    int n = arr.length;\n    for (int i = 0; i < n - 1; i++) {\n        int minIndex = i;\n        for (int j = i + 1; j < n; j++) {\n            if (arr[j] < arr[minIndex]) {\n                minIndex = j;\n            }\n        }\n        int temp = arr[minIndex];\n        arr[minIndex] = arr[i];\n        arr[i] = temp;\n    }\n}",
            false
        );
        codes = new HashMap<>();
        codes.put("Java", ai.getCode());
        codes.put("Python", "# Python - Selection Sort\ndef selection_sort(arr):\n    n = len(arr)\n    for i in range(n-1):\n        min_index = i\n        for j in range(i+1, n):\n            if arr[j] < arr[min_index]:\n                min_index = j\n        arr[i], arr[min_index] = arr[min_index], arr[i]\n");
        codes.put("JavaScript", "// JavaScript - Selection Sort\nfunction selectionSort(arr) {\n    const n = arr.length;\n    for (let i = 0; i < n - 1; i++) {\n        let minIndex = i;\n        for (let j = i + 1; j < n; j++) {\n            if (arr[j] < arr[minIndex]) minIndex = j;\n        }\n        [arr[i], arr[minIndex]] = [arr[minIndex], arr[i]];\n    }\n}\n");
        codes.put("C++", "// C++ - Selection Sort\nvoid selectionSort(vector<int>& arr) {\n    int n = arr.size();\n    for (int i = 0; i < n - 1; ++i) {\n        int minIndex = i;\n        for (int j = i + 1; j < n; ++j) if (arr[j] < arr[minIndex]) minIndex = j;\n        swap(arr[i], arr[minIndex]);\n    }\n}\n");
        codes.put("C#", "// C# - Selection Sort\nvoid SelectionSort(int[] arr) {\n    for (int i = 0; i < arr.Length - 1; i++) {\n        int minIndex = i;\n        for (int j = i + 1; j < arr.Length; j++) if (arr[j] < arr[minIndex]) minIndex = j;\n        int tmp = arr[minIndex]; arr[minIndex] = arr[i]; arr[i] = tmp;\n    }\n}\n");
        codes.put("TypeScript", "// TypeScript - Selection Sort\nfunction selectionSort(arr: number[]): void {\n    const n = arr.length;\n    for (let i = 0; i < n - 1; i++) {\n        let minIndex = i;\n        for (let j = i + 1; j < n; j++) if (arr[j] < arr[minIndex]) minIndex = j;\n        [arr[i], arr[minIndex]] = [arr[minIndex], arr[i]];\n    }\n}\n");
        ai.setCodes(codes);
        algorithms.add(ai);

        ai = new AlgorithmInfo(
            "binary_java",
            "Binary Search",
            "Search a sorted array by repeatedly halving the search range.",
            "Java",
            "Searching",
            List.of(1, 3, 5, 7, 9),
            "// Java - Binary Search\nint binarySearch(int[] arr, int target) {\n    int left = 0, right = arr.length - 1;\n    while (left <= right) {\n        int mid = left + (right - left) / 2;\n        if (arr[mid] == target) {\n            return mid;\n        } else if (arr[mid] < target) {\n            left = mid + 1;\n        } else {\n            right = mid - 1;\n        }\n    }\n    return -1;\n}",
            true
        );
        codes = new HashMap<>();
        codes.put("Java", ai.getCode());
        codes.put("Python", "# Python - Binary Search\ndef binary_search(arr, target):\n    left, right = 0, len(arr) - 1\n    while left <= right:\n        mid = (left + right) // 2\n        if arr[mid] == target:\n            return mid\n        elif arr[mid] < target:\n            left = mid + 1\n        else:\n            right = mid - 1\n    return -1\n");
        codes.put("JavaScript", "// JavaScript - Binary Search\nfunction binarySearch(arr, target) {\n    let left = 0, right = arr.length - 1;\n    while (left <= right) {\n        const mid = Math.floor((left + right) / 2);\n        if (arr[mid] === target) return mid;\n        if (arr[mid] < target) left = mid + 1;\n        else right = mid - 1;\n    }\n    return -1;\n}\n");
        codes.put("C++", "// C++ - Binary Search\nint binarySearch(const vector<int>& arr, int target) {\n    int left = 0, right = arr.size() - 1;\n    while (left <= right) {\n        int mid = left + (right - left) / 2;\n        if (arr[mid] == target) return mid;\n        if (arr[mid] < target) left = mid + 1;\n        else right = mid - 1;\n    }\n    return -1;\n}\n");
        codes.put("C#", "// C# - Binary Search\nint BinarySearch(int[] arr, int target) {\n    int left = 0, right = arr.Length - 1;\n    while (left <= right) {\n        int mid = left + (right - left) / 2;\n        if (arr[mid] == target) return mid;\n        if (arr[mid] < target) left = mid + 1;\n        else right = mid - 1;\n    }\n    return -1;\n}\n");
        codes.put("TypeScript", "// TypeScript - Binary Search\nfunction binarySearch(arr: number[], target: number): number {\n    let left = 0, right = arr.length - 1;\n    while (left <= right) {\n        const mid = Math.floor((left + right) / 2);\n        if (arr[mid] === target) return mid;\n        if (arr[mid] < target) left = mid + 1;\n        else right = mid - 1;\n    }\n    return -1;\n}\n");
        ai.setCodes(codes);
        algorithms.add(ai);

        ai = new AlgorithmInfo(
            "remove_dup",
            "Remove Duplicates",
            "Remove duplicates from a sorted array while keeping only unique values.",
            "C++",
            "Array",
            List.of(1, 1, 2, 3, 3, 4),
            "// C++ - Remove Duplicates from Sorted Array\nint removeDuplicates(vector<int>& nums) {\n    if (nums.empty()) return 0;\n    int count = 1;\n    for (int i = 1; i < nums.size(); i++) {\n        if (nums[i] != nums[i - 1]) {\n            nums[count] = nums[i];\n            count++;\n        }\n    }\n    return count;\n}",
            false
        );
        codes = new HashMap<>();
        codes.put("C++", ai.getCode());
        codes.put("Java", "// Java - Remove Duplicates\nint removeDuplicates(int[] nums) {\n    if (nums.length == 0) return 0;\n    int count = 1;\n    for (int i = 1; i < nums.length; i++) {\n        if (nums[i] != nums[i-1]) { nums[count++] = nums[i]; }\n    }\n    return count;\n}\n");
        codes.put("Python", "# Python - Remove Duplicates\ndef remove_duplicates(nums):\n    if not nums: return 0\n    count = 1\n    for i in range(1, len(nums)):\n        if nums[i] != nums[i-1]:\n            nums[count] = nums[i]\n            count += 1\n    return count\n");
        codes.put("JavaScript", "// JavaScript - Remove Duplicates\nfunction removeDuplicates(nums) {\n    if (nums.length === 0) return 0;\n    let count = 1;\n    for (let i = 1; i < nums.length; i++) {\n        if (nums[i] !== nums[i-1]) { nums[count++] = nums[i]; }\n    }\n    return count;\n}\n");
        codes.put("C#", "// C# - Remove Duplicates\nint RemoveDuplicates(int[] nums) {\n    if (nums.Length == 0) return 0;\n    int count = 1;\n    for (int i = 1; i < nums.Length; i++) {\n        if (nums[i] != nums[i-1]) { nums[count++] = nums[i]; }\n    }\n    return count;\n}\n");
        codes.put("TypeScript", "// TypeScript - Remove Duplicates\nfunction removeDuplicates(nums: number[]): number {\n    if (nums.length === 0) return 0;\n    let count = 1;\n    for (let i = 1; i < nums.length; i++) {\n        if (nums[i] !== nums[i-1]) { nums[count++] = nums[i]; }\n    }\n    return count;\n}\n");
        ai.setCodes(codes);
        algorithms.add(ai);

        ai = new AlgorithmInfo(
            "binary",
            "Binary Search",
            "Search a sorted array by repeatedly halving the search range.",
            "Python",
            "Searching",
            List.of(1, 3, 5, 7, 9),
            "# Python - Binary Search\ndef binary_search(arr, target):\n    left, right = 0, len(arr) - 1\n    while left <= right:\n        mid = (left + right) // 2\n        if arr[mid] == target:\n            return mid\n        elif arr[mid] < target:\n            left = mid + 1\n        else:\n            right = mid - 1\n    return -1\n",
            true
        );
        codes = new HashMap<>();
        codes.put("Python", ai.getCode());
        codes.put("Java", "// Java - Binary Search\nint binarySearch(int[] arr, int target) {\n    int left = 0, right = arr.length - 1;\n    while (left <= right) {\n        int mid = left + (right - left) / 2;\n        if (arr[mid] == target) return mid;\n        if (arr[mid] < target) left = mid + 1;\n        else right = mid - 1;\n    }\n    return -1;\n}\n");
        codes.put("JavaScript", "// JavaScript - Binary Search\nfunction binarySearch(arr, target) {\n    let left = 0, right = arr.length - 1;\n    while (left <= right) {\n        const mid = Math.floor((left + right) / 2);\n        if (arr[mid] === target) return mid;\n        if (arr[mid] < target) left = mid + 1;\n        else right = mid - 1;\n    }\n    return -1;\n}\n");
        codes.put("C++", "// C++ - Binary Search\nint binarySearch(const vector<int>& arr, int target) {\n    int left = 0, right = arr.size() - 1;\n    while (left <= right) {\n        int mid = left + (right - left) / 2;\n        if (arr[mid] == target) return mid;\n        if (arr[mid] < target) left = mid + 1;\n        else right = mid - 1;\n    }\n    return -1;\n}\n");
        codes.put("C#", "// C# - Binary Search\nint BinarySearch(int[] arr, int target) {\n    int left = 0, right = arr.Length - 1;\n    while (left <= right) {\n        int mid = left + (right - left) / 2;\n        if (arr[mid] == target) return mid;\n        if (arr[mid] < target) left = mid + 1;\n        else right = mid - 1;\n    }\n    return -1;\n}\n");
        codes.put("TypeScript", "// TypeScript - Binary Search\nfunction binarySearch(arr: number[], target: number): number {\n    let left = 0, right = arr.length - 1;\n    while (left <= right) {\n        const mid = Math.floor((left + right) / 2);\n        if (arr[mid] === target) return mid;\n        if (arr[mid] < target) left = mid + 1;\n        else right = mid - 1;\n    }\n    return -1;\n}\n");
        ai.setCodes(codes);
        algorithms.add(ai);

        ai = new AlgorithmInfo(
            "insertion",
            "Insertion Sort",
            "Build the sorted section of the array one element at a time.",
            "C#",
            "Sorting",
            List.of(8, 3, 5, 1, 4),
            "// C# - Insertion Sort\nvoid InsertionSort(int[] arr) {\n    for (int i = 1; i < arr.Length; i++) {\n        int key = arr[i];\n        int j = i - 1;\n        while (j >= 0 && arr[j] > key) {\n            arr[j + 1] = arr[j];\n            j--;\n        }\n        arr[j + 1] = key;\n    }\n}",
            false
        );
        codes = new HashMap<>();
        codes.put("C#", ai.getCode());
        codes.put("Java", "// Java - Insertion Sort\nvoid insertionSort(int[] arr) {\n    for (int i = 1; i < arr.length; i++) {\n        int key = arr[i];\n        int j = i - 1;\n        while (j >= 0 && arr[j] > key) { arr[j + 1] = arr[j]; j--; }\n        arr[j + 1] = key;\n    }\n}\n");
        codes.put("Python", "# Python - Insertion Sort\ndef insertion_sort(arr):\n    for i in range(1, len(arr)):\n        key = arr[i]\n        j = i - 1\n        while j >= 0 and arr[j] > key:\n            arr[j+1] = arr[j]\n            j -= 1\n        arr[j+1] = key\n");
        codes.put("JavaScript", "// JavaScript - Insertion Sort\nfunction insertionSort(arr) {\n    for (let i = 1; i < arr.length; i++) {\n        let key = arr[i];\n        let j = i - 1;\n        while (j >= 0 && arr[j] > key) { arr[j + 1] = arr[j]; j--; }\n        arr[j + 1] = key;\n    }\n}\n");
        codes.put("C++", "// C++ - Insertion Sort\nvoid insertionSort(vector<int>& arr) {\n    for (size_t i = 1; i < arr.size(); ++i) {\n        int key = arr[i];\n        int j = i - 1;\n        while (j >= 0 && arr[j] > key) { arr[j + 1] = arr[j]; j--; }\n        arr[j + 1] = key;\n    }\n}\n");
        codes.put("TypeScript", "// TypeScript - Insertion Sort\nfunction insertionSort(arr: number[]): void {\n    for (let i = 1; i < arr.length; i++) {\n        const key = arr[i];\n        let j = i - 1;\n        while (j >= 0 && arr[j] > key) { arr[j + 1] = arr[j]; j--; }\n        arr[j + 1] = key;\n    }\n}\n");
        ai.setCodes(codes);
        algorithms.add(ai);

        ai = new AlgorithmInfo(
            "two_sum",
            "Two Pointers Sum",
            "Find two numbers in a sorted array whose sum equals the target.",
            "JavaScript",
            "Two Pointers",
            List.of(1, 2, 4, 6, 8),
            "// JavaScript - Two Pointers\nfunction twoSum(arr, target) {\n    let left = 0, right = arr.length - 1;\n    while (left < right) {\n        const sum = arr[left] + arr[right];\n        if (sum === target) return [left, right];\n        if (sum < target) left++;\n        else right--;\n    }\n    return [-1, -1];\n}",
            true
        );
        codes = new HashMap<>();
        codes.put("JavaScript", ai.getCode());
        codes.put("Java", "// Java - Two Pointers Sum\nint[] twoSum(int[] arr, int target) {\n    int left = 0, right = arr.length - 1;\n    while (left < right) {\n        int sum = arr[left] + arr[right];\n        if (sum == target) return new int[]{left, right};\n        if (sum < target) left++; else right--;\n    }\n    return new int[]{-1, -1};\n}\n");
        codes.put("Python", "# Python - Two Pointers Sum\ndef two_sum(arr, target):\n    left, right = 0, len(arr) - 1\n    while left < right:\n        s = arr[left] + arr[right]\n        if s == target: return (left, right)\n        if s < target: left += 1\n        else: right -= 1\n    return (-1, -1)\n");
        codes.put("C++", "// C++ - Two Pointers Sum\npair<int,int> twoSum(const vector<int>& arr, int target) {\n    int left = 0, right = arr.size() - 1;\n    while (left < right) {\n        int sum = arr[left] + arr[right];\n        if (sum == target) return {left, right};\n        if (sum < target) left++; else right--;\n    }\n    return {-1, -1};\n}\n");
        codes.put("C#", "// C# - Two Pointers Sum\n(int,int) TwoSum(int[] arr, int target) {\n    int left = 0, right = arr.Length - 1;\n    while (left < right) {\n        int sum = arr[left] + arr[right];\n        if (sum == target) return (left, right);\n        if (sum < target) left++; else right--;\n    }\n    return (-1, -1);\n}\n");
        codes.put("TypeScript", "// TypeScript - Two Pointers Sum\nfunction twoSum(arr: number[], target: number): [number, number] {\n    let left = 0, right = arr.length - 1;\n    while (left < right) {\n        const sum = arr[left] + arr[right];\n        if (sum === target) return [left, right];\n        if (sum < target) left++; else right--;\n    }\n    return [-1, -1];\n}\n");
        ai.setCodes(codes);
        algorithms.add(ai);
    }

    public List<AlgorithmInfo> listAlgorithms() {
        return algorithms;
    }

    public AlgorithmResult runById(String id, Object input, Object target) {
        Optional<AlgorithmInfo> algorithm = algorithms.stream().filter(a -> a.getId().equals(id)).findFirst();
        if (algorithm.isEmpty()) {
            AlgorithmResult result = new AlgorithmResult();
            result.setValid(false);
            result.setCorrect(false);
            result.setExplanation("Unknown algorithm ID: " + id);
            return result;
        }

        List<Integer> values = extractIntegerList(input);
        if (values.isEmpty()) {
            values = algorithm.get().getDefaultInput();
        }

        switch (id) {
            case "bubble":
                return runBubbleSort(values);
            case "selection":
                return runSelectionSort(values);
            case "remove_dup":
                return runRemoveDuplicates(values);
            case "binary":
            case "binary_java":
                return runBinarySearch(values, extractInteger(target));
            case "insertion":
                return runInsertionSort(values);
            case "two_sum":
                return runTwoSum(values, extractInteger(target));
            default:
                AlgorithmResult result = new AlgorithmResult();
                result.setValid(false);
                result.setCorrect(false);
                result.setExplanation("Algorithm implementation is missing for: " + id);
                return result;
        }
    }

    private List<Integer> extractIntegerList(Object raw) {
        if (raw == null) {
            return new ArrayList<>();
        }
        if (raw instanceof List<?>) {
            List<?> list = (List<?>) raw;
            return list.stream().map(this::extractInteger).filter(v -> v != null).collect(Collectors.toList());
        }
        if (raw instanceof String) {
            String text = ((String) raw).trim();
            if (text.isEmpty()) {
                return new ArrayList<>();
            }
            String normalized = text.replaceAll("[^0-9\\-]+", " ").trim();
            if (normalized.isEmpty()) {
                return new ArrayList<>();
            }
            String[] parts = normalized.split("\\s+");
            List<Integer> parsed = new ArrayList<>();
            for (String part : parts) {
                Integer value = extractInteger(part);
                if (value != null) {
                    parsed.add(value);
                }
            }
            return parsed;
        }
        Integer fromNumber = extractInteger(raw);
        return fromNumber == null ? new ArrayList<>() : List.of(fromNumber);
    }

    private Integer extractInteger(Object raw) {
        if (raw == null) {
            return null;
        }
        if (raw instanceof Integer) {
            return (Integer) raw;
        }
        if (raw instanceof Number) {
            return ((Number) raw).intValue();
        }
        try {
            return Integer.parseInt(raw.toString().trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private AlgorithmResult runBubbleSort(List<Integer> values) {
        AlgorithmResult result = baseResult("Bubble Sort", "Sorting", "Bubble sort repeatedly compares adjacent elements and swaps them.", "O(n²)", "O(1)", "Bubble sort works by pushing larger values to the end of the list during each pass.");
        result.setCodeLines(List.of(
                new CodeLine("void bubbleSort(int[] arr) {", "Start bubble sort."),
                new CodeLine("    int n = arr.length;", "Store the array length."),
                new CodeLine("    for (int i = 0; i < n - 1; i++) {", "Repeat passes over the array."),
                new CodeLine("        for (int j = 0; j < n - i - 1; j++) {", "Compare each pair of adjacent items."),
                new CodeLine("            if (arr[j] > arr[j + 1]) {", "If the left item is larger, swap."),
                new CodeLine("                int temp = arr[j];", "Save the left item."),
                new CodeLine("                arr[j] = arr[j + 1];", "Move the smaller item left."),
                new CodeLine("                arr[j + 1] = temp;", "Place the saved item to the right."),
                new CodeLine("            }", "End swap condition."),
                new CodeLine("        }", "End inner loop."),
                new CodeLine("    }", "End outer loop."),
                new CodeLine("}", "Bubble sort is complete.")
        ));

        List<ExecutionStep> steps = new ArrayList<>();
        List<Integer> current = new ArrayList<>(values);
        List<Integer> done = new ArrayList<>();
        for (int i = 0; i < current.size() - 1; i++) {
            for (int j = 0; j < current.size() - i - 1; j++) {
                ExecutionStep step = new ExecutionStep();
                step.setArr(new ArrayList<>(current));
                step.setHighlight(List.of(j, j + 1));
                step.setPointers(Map.of("0", "j", "1", "j+1"));
                step.setActiveLine(4);
                step.setMsg("Compare the pair at indexes " + j + " and " + (j + 1) + ".");
                steps.add(step);
                if (current.get(j) > current.get(j + 1)) {
                    int temp = current.get(j);
                    current.set(j, current.get(j + 1));
                    current.set(j + 1, temp);
                    ExecutionStep swapStep = new ExecutionStep();
                    swapStep.setArr(new ArrayList<>(current));
                    swapStep.setSwap(List.of(j, j + 1));
                    swapStep.setPointers(Map.of("0", "j", "1", "j+1"));
                    swapStep.setActiveLine(5);
                    swapStep.setMsg("Swap the items because the left value is greater than the right value.");
                    steps.add(swapStep);
                }
            }
            done.add(current.size() - 1 - i);
            ExecutionStep doneStep = new ExecutionStep();
            doneStep.setArr(new ArrayList<>(current));
            doneStep.setDone(new ArrayList<>(done));
            doneStep.setMsg("Element at index " + (current.size() - 1 - i) + " is now in its final position.");
            doneStep.setActiveLine(9);
            steps.add(doneStep);
        }

        result.setSteps(steps);
        return result;
    }

    private AlgorithmResult runInsertionSort(List<Integer> values) {
        AlgorithmResult result = baseResult("Insertion Sort", "Sorting", "Build the sorted section of the array one element at a time.", "O(n²)", "O(1)", "Insertion sort shifts larger elements to the right and inserts the current value in its correct position.");
        result.setCodeLines(List.of(
                new CodeLine("void InsertionSort(int[] arr) {", "Begin insertion sort."),
                new CodeLine("    for (int i = 1; i < arr.Length; i++) {", "Iterate through the array starting from the second element."),
                new CodeLine("        int key = arr[i];", "Keep the current value to insert into the sorted prefix."),
                new CodeLine("        int j = i - 1;", "Start comparing to the previous sorted element."),
                new CodeLine("        while (j >= 0 && arr[j] > key) {", "Move elements larger than key one position to the right."),
                new CodeLine("            arr[j + 1] = arr[j];", "Shift an element to make space for key."),
                new CodeLine("            j--;", "Move the pointer left in the sorted prefix."),
                new CodeLine("        }", "Stop when the correct insertion point is found."),
                new CodeLine("        arr[j + 1] = key;", "Insert the key into its sorted position."),
                new CodeLine("    }", "Continue to the next element."),
                new CodeLine("}", "Insertion sort is complete.")
        ));

        List<ExecutionStep> steps = new ArrayList<>();
        List<Integer> current = new ArrayList<>(values);
        for (int i = 1; i < current.size(); i++) {
            int key = current.get(i);
            int j = i - 1;
            ExecutionStep selectStep = new ExecutionStep();
            selectStep.setArr(new ArrayList<>(current));
            selectStep.setHighlight(List.of(i));
            selectStep.setPointers(Map.of("0", "i", "1", "key"));
            selectStep.setActiveLine(3);
            selectStep.setMsg("Select the element at index " + i + " as the key to insert.");
            steps.add(selectStep);

            while (j >= 0 && current.get(j) > key) {
                ExecutionStep compareStep = new ExecutionStep();
                compareStep.setArr(new ArrayList<>(current));
                compareStep.setHighlight(List.of(j));
                compareStep.setSecondary(List.of(j + 1));
                compareStep.setPointers(Map.of("0", "j", "1", "key"));
                compareStep.setActiveLine(5);
                compareStep.setMsg("Compare key with element at index " + j + ".");
                steps.add(compareStep);

                current.set(j + 1, current.get(j));
                ExecutionStep shiftStep = new ExecutionStep();
                shiftStep.setArr(new ArrayList<>(current));
                shiftStep.setSwap(List.of(j, j + 1));
                shiftStep.setPointers(Map.of("0", "j", "1", "key"));
                shiftStep.setActiveLine(6);
                shiftStep.setMsg("Shift the larger element at index " + j + " to the right.");
                steps.add(shiftStep);
                j--;
            }
            current.set(j + 1, key);
            ExecutionStep insertStep = new ExecutionStep();
            insertStep.setArr(new ArrayList<>(current));
            insertStep.setHighlight(List.of(j + 1));
            insertStep.setPointers(Map.of("0", "insertPos", "1", "key"));
            insertStep.setActiveLine(8);
            insertStep.setMsg("Insert the key into its sorted position at index " + (j + 1) + ".");
            steps.add(insertStep);
        }

        result.setSteps(steps);
        return result;
    }

    private AlgorithmResult runSelectionSort(List<Integer> values) {
        AlgorithmResult result = baseResult("Selection Sort", "Sorting", "Selection sort finds the smallest remaining item and moves it into place.", "O(n²)", "O(1)", "Selection sort builds the sorted part of the list from left to right.");
        result.setCodeLines(List.of(
                new CodeLine("void selectionSort(int[] arr) {", "Start selection sort."),
                new CodeLine("    int n = arr.length;", "Store the array size."),
                new CodeLine("    for (int i = 0; i < n - 1; i++) {", "Choose the next position to fill."),
                new CodeLine("        int minIndex = i;", "Assume the current position holds the smallest item."),
                new CodeLine("        for (int j = i + 1; j < n; j++) {", "Check the rest of the array."),
                new CodeLine("            if (arr[j] < arr[minIndex]) {", "Found a smaller item."),
                new CodeLine("                minIndex = j;", "Remember its position."),
                new CodeLine("            }", "End comparison."),
                new CodeLine("        }", "End inner loop."),
                new CodeLine("        int temp = arr[minIndex];", "Swap the smallest item into place."),
                new CodeLine("        arr[minIndex] = arr[i];", "Move the current item to the min position."),
                new CodeLine("        arr[i] = temp;", "Place the smallest item at the front."),
                new CodeLine("    }", "End outer loop."),
                new CodeLine("}", "Selection sort is complete.")
        ));

        List<ExecutionStep> steps = new ArrayList<>();
        List<Integer> current = new ArrayList<>(values);
        for (int i = 0; i < current.size() - 1; i++) {
            int minIndex = i;
            ExecutionStep initStep = new ExecutionStep();
            initStep.setArr(new ArrayList<>(current));
            initStep.setHighlight(List.of(i));
            initStep.setPointers(Map.of("0", "i", "1", "minIndex"));
            initStep.setActiveLine(3);
            initStep.setMsg("Start selecting the smallest item for index " + i + ".");
            steps.add(initStep);
            for (int j = i + 1; j < current.size(); j++) {
                ExecutionStep compareStep = new ExecutionStep();
                compareStep.setArr(new ArrayList<>(current));
                compareStep.setHighlight(List.of(minIndex, j));
                compareStep.setPointers(Map.of("0", "i", "1", "j", "2", "minIndex"));
                compareStep.setActiveLine(5);
                compareStep.setMsg("Compare current minimum with the item at index " + j + ".");
                steps.add(compareStep);
                if (current.get(j) < current.get(minIndex)) {
                    minIndex = j;
                    ExecutionStep foundStep = new ExecutionStep();
                    foundStep.setArr(new ArrayList<>(current));
                    foundStep.setSecondary(List.of(minIndex));
                    foundStep.setPointers(Map.of("0", "i", "1", "minIndex"));
                    foundStep.setActiveLine(6);
                    foundStep.setMsg("Found a new minimum value at index " + j + ".");
                    steps.add(foundStep);
                }
            }
            int temp = current.get(minIndex);
            current.set(minIndex, current.get(i));
            current.set(i, temp);
            ExecutionStep swapStep = new ExecutionStep();
            swapStep.setArr(new ArrayList<>(current));
            swapStep.setSwap(List.of(i, minIndex));
            swapStep.setDone(List.of(i));
            swapStep.setPointers(Map.of("0", "i", "1", "minIndex"));
            swapStep.setActiveLine(9);
            swapStep.setMsg("Swap the smallest item into the sorted position " + i + ".");
            steps.add(swapStep);
        }

        result.setSteps(steps);
        return result;
    }

    private AlgorithmResult runRemoveDuplicates(List<Integer> values) {
        AlgorithmResult result = baseResult("Remove Duplicates", "Array", "Remove duplicates from a sorted array while preserving order.", "O(n)", "O(1)", "The algorithm overwrites duplicate entries so the first section of the array contains only unique values.");
        result.setCodeLines(List.of(
                new CodeLine("// Remove duplicates (preserve first occurrence order)", "Start duplicate removal."),
                new CodeLine("List<Integer> unique = new ArrayList<>();", "Collect unique items in order."),
                new CodeLine("Set<Integer> seen = new HashSet<>();", "Track seen values."),
                new CodeLine("for (int i = 0; i < nums.size(); i++) {", "Examine each item."),
                new CodeLine("    if (!seen.contains(nums.get(i))) {", "If unseen, append to unique."),
                new CodeLine("        unique.add(nums.get(i));", "Add to unique list."),
                new CodeLine("        seen.add(nums.get(i));", "Mark as seen."),
                new CodeLine("    } else {", "Otherwise skip as duplicate."),
                new CodeLine("        // duplicate - ignored", "Skip duplicates."),
                new CodeLine("    }", "End check."),
                new CodeLine("}", "Done; unique contains first occurrences."),
                new CodeLine("return unique;", "Return the deduplicated list.")
        ));

        List<ExecutionStep> steps = new ArrayList<>();
        List<Integer> current = new ArrayList<>(values);
        if (current.isEmpty()) {
            ExecutionStep step = new ExecutionStep();
            step.setArr(new ArrayList<>());
            step.setMsg("No values to process.");
            step.setActiveLine(1);
            steps.add(step);
            result.setSteps(steps);
            return result;
        }

        List<Integer> unique = new ArrayList<>();
        java.util.Set<Integer> seen = new java.util.HashSet<>();

        // initial empty view
        ExecutionStep init = new ExecutionStep();
        init.setArr(new ArrayList<>(unique));
        init.setActiveLine(1);
        init.setMsg("Start with an empty unique list.");
        steps.add(init);

        for (int i = 0; i < current.size(); i++) {
            int val = current.get(i);
            ExecutionStep compareStep = new ExecutionStep();
            // show only the collected unique prefix (candidate is inspected but hidden)
            List<Integer> view = new ArrayList<>(unique);
            compareStep.setArr(new ArrayList<>(view));
            // highlight the last unique item to indicate comparison location
            if (!view.isEmpty()) {
                compareStep.setHighlight(List.of(view.size() - 1));
                compareStep.setPointers(Map.of(String.valueOf(view.size() - 1), "count - 1"));
            }
            compareStep.setActiveLine(3);
            compareStep.setMsg("Inspect candidate value " + val + " (hidden if duplicate).");
            steps.add(compareStep);

            if (!seen.contains(val)) {
                seen.add(val);
                unique.add(val);

                ExecutionStep writeStep = new ExecutionStep();
                writeStep.setArr(new ArrayList<>(unique));
                writeStep.setSecondary(List.of(unique.size() - 1));
                writeStep.setDone(IntStream.range(0, unique.size()).boxed().collect(Collectors.toList()));
                writeStep.setPointers(Map.of(String.valueOf(unique.size() - 1), "count"));
                writeStep.setActiveLine(5);
                writeStep.setMsg("Value " + val + " is new — add to unique list.");
                steps.add(writeStep);
            } else {
                ExecutionStep hidden = new ExecutionStep();
                hidden.setArr(new ArrayList<>(unique));
                hidden.setActiveLine(7);
                hidden.setMsg("Value " + val + " is a duplicate and is hidden.");
                steps.add(hidden);
            }
        }

        ExecutionStep doneStep = new ExecutionStep();
        doneStep.setArr(new ArrayList<>(unique));
        doneStep.setDone(IntStream.range(0, unique.size()).boxed().collect(Collectors.toList()));
        doneStep.setMsg("Completed — unique values only.");
        doneStep.setActiveLine(10);
        steps.add(doneStep);

        result.setSteps(steps);
        return result;
    }

    private AlgorithmResult runBinarySearch(List<Integer> values, Integer target) {
        AlgorithmResult result = baseResult("Binary Search", "Searching", "Binary search locates a target value in a sorted array by halving the search range.", "O(log n)", "O(1)", "Binary search repeatedly splits the range until the target is found or the range is empty.");
        result.setCodeLines(List.of(
                new CodeLine("def binary_search(arr, target):", "Start binary search."),
                new CodeLine("    left, right = 0, len(arr) - 1", "Set the search boundaries."),
                new CodeLine("    while left <= right:", "Continue while the range is valid."),
                new CodeLine("        mid = (left + right) // 2", "Check the middle element."),
                new CodeLine("        if arr[mid] == target:", "If the middle item matches the target, return it."),
                new CodeLine("            return mid", "Return the found index."),
                new CodeLine("        elif arr[mid] < target:", "If the middle item is too small, search the right half."),
                new CodeLine("            left = mid + 1", "Move the left boundary right."),
                new CodeLine("        else:", "Otherwise, search the left half."),
                new CodeLine("            right = mid - 1", "Move the right boundary left."),
                new CodeLine("    return -1", "If not found, return -1.")
        ));

        List<ExecutionStep> steps = new ArrayList<>();
        if (target == null) {
            AlgorithmResult invalid = new AlgorithmResult();
            invalid.setValid(false);
            invalid.setCorrect(false);
            invalid.setExplanation("Binary search requires a target value.");
            return invalid;
        }
        List<Integer> current = new ArrayList<>(values);
        int left = 0;
        int right = current.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            ExecutionStep step = new ExecutionStep();
            step.setArr(new ArrayList<>(current));
            step.setHighlight(List.of(left, mid, right));
            Map<String, String> pointers = new HashMap<>();
            pointers.put(String.valueOf(left), "left");
            pointers.put(String.valueOf(mid), "mid");
            pointers.put(String.valueOf(right), "right");
            step.setPointers(pointers);
            step.setActiveLine(3);
            step.setMsg("Check the middle value at index " + mid + ".");
            steps.add(step);
            if (current.get(mid).equals(target)) {
                ExecutionStep found = new ExecutionStep();
                found.setArr(new ArrayList<>(current));
                found.setDone(List.of(mid));
                found.setPointers(pointers);
                found.setActiveLine(4);
                found.setMsg("Found the target at index " + mid + ".");
                steps.add(found);
                result.setExplanation("Target found at index " + mid + ".");
                result.setSteps(steps);
                return result;
            }
            if (current.get(mid) < target) {
                left = mid + 1;
                ExecutionStep moveRight = new ExecutionStep();
                moveRight.setArr(new ArrayList<>(current));
                moveRight.setHighlight(List.of(left, right));
                moveRight.setPointers(pointers);
                moveRight.setActiveLine(7);
                moveRight.setMsg("Target is larger than the middle value, search the right side.");
                steps.add(moveRight);
            } else {
                right = mid - 1;
                ExecutionStep moveLeft = new ExecutionStep();
                moveLeft.setArr(new ArrayList<>(current));
                moveLeft.setHighlight(List.of(left, right));
                moveLeft.setPointers(pointers);
                moveLeft.setActiveLine(9);
                moveLeft.setMsg("Target is smaller than the middle value, search the left side.");
                steps.add(moveLeft);
            }
        }

        ExecutionStep notFound = new ExecutionStep();
        notFound.setArr(new ArrayList<>(current));
        notFound.setMsg("The target value is not in the array.");
        notFound.setActiveLine(10);
        steps.add(notFound);
        result.setExplanation("Target not found in the array.");
        result.setSteps(steps);
        return result;
    }

    private AlgorithmResult runTwoSum(List<Integer> values, Integer target) {
        AlgorithmResult result = baseResult("Two Pointers Sum", "Two Pointers", "Use two pointers on a sorted array to find a pair that sums to a target.", "O(n)", "O(1)", "Move the left or right pointer inward depending on the current sum.");
        result.setCodeLines(List.of(
                new CodeLine("function twoSum(arr, target) {", "Start the two-pointer search."),
                new CodeLine("    let left = 0, right = arr.length - 1;", "Set the pointers at each end."),
                new CodeLine("    while (left < right) {", "Search until pointers cross."),
                new CodeLine("        const sum = arr[left] + arr[right];", "Calculate the current pair sum."),
                new CodeLine("        if (sum === target) return [left, right];", "If it matches the target, return the pair."),
                new CodeLine("        if (sum < target) left++;", "If the sum is too small, move the left pointer right."),
                new CodeLine("        else right--;", "If the sum is too large, move the right pointer left."),
                new CodeLine("    }", "End the search."),
                new CodeLine("    return [-1, -1];", "No matching pair was found."),
                new CodeLine("}", "Two-pointer search is complete.")
        ));

        if (target == null) {
            AlgorithmResult invalid = new AlgorithmResult();
            invalid.setValid(false);
            invalid.setCorrect(false);
            invalid.setExplanation("Two pointers algorithm requires a target value.");
            return invalid;
        }

        List<Integer> current = new ArrayList<>(values);
        int left = 0;
        int right = current.size() - 1;
        List<ExecutionStep> steps = new ArrayList<>();
        while (left < right) {
            ExecutionStep step = new ExecutionStep();
            step.setArr(new ArrayList<>(current));
            step.setHighlight(List.of(left, right));
            step.setPointers(Map.of(String.valueOf(left), "left", String.valueOf(right), "right"));
            step.setActiveLine(3);
            step.setMsg("Check the pair at indexes " + left + " and " + right + ".");
            steps.add(step);
            int sum = current.get(left) + current.get(right);
            if (sum == target) {
                ExecutionStep found = new ExecutionStep();
                found.setArr(new ArrayList<>(current));
                found.setDone(List.of(left, right));
                found.setPointers(Map.of(String.valueOf(left), "left", String.valueOf(right), "right"));
                found.setActiveLine(4);
                found.setMsg("Found a pair that sums to the target.");
                steps.add(found);
                result.setExplanation("Pair found at indexes " + left + " and " + right + ".");
                result.setSteps(steps);
                return result;
            }
            if (sum < target) {
                left++;
                ExecutionStep moveLeft = new ExecutionStep();
                moveLeft.setArr(new ArrayList<>(current));
                moveLeft.setPointers(Map.of(String.valueOf(left), "left", String.valueOf(right), "right"));
                moveLeft.setActiveLine(5);
                moveLeft.setMsg("Sum is too small, move the left pointer right.");
                steps.add(moveLeft);
            } else {
                right--;
                ExecutionStep moveRight = new ExecutionStep();
                moveRight.setArr(new ArrayList<>(current));
                moveRight.setPointers(Map.of(String.valueOf(left), "left", String.valueOf(right), "right"));
                moveRight.setActiveLine(6);
                moveRight.setMsg("Sum is too large, move the right pointer left.");
                steps.add(moveRight);
            }
        }

        ExecutionStep notFound = new ExecutionStep();
        notFound.setArr(new ArrayList<>(current));
        notFound.setMsg("No pair found that matches the target.");
        notFound.setActiveLine(8);
        steps.add(notFound);
        result.setExplanation("No matching pair exists in the array.");
        result.setSteps(steps);
        return result;
    }

    private AlgorithmResult baseResult(String name, String category, String description, String timeComplexity, String spaceComplexity, String explanation) {
        AlgorithmResult result = new AlgorithmResult();
        result.setValid(true);
        result.setCorrect(true);
        result.setAlgorithmName(name);
        result.setCategory(category);
        result.setDescription(description);
        result.setTimeComplexity(timeComplexity);
        result.setSpaceComplexity(spaceComplexity);
        result.setExplanation(explanation);
        result.setHowItWorks(List.of("Read the array state.", "Follow the pointers and comparisons.", "Observe how the algorithm moves values into place."));
        return result;
    }
}
