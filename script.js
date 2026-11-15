// Student Attendance Calculator - beginner-friendly logic for viva explanation

// Get references to input fields, buttons, result elements, and status message
const totalClassesInput = document.getElementById("totalClasses");
const absentClassesInput = document.getElementById("absentClasses");
const calculateBtn = document.getElementById("calculateBtn");
const clearBtn = document.getElementById("clearBtn");

const attendedResult = document.getElementById("attendedResult");
const absentResult = document.getElementById("absentResult");
const percentageResult = document.getElementById("percentageResult");
const leavesResult = document.getElementById("leavesResult");
const statusMessage = document.getElementById("statusMessage");

// Helper: show a message in the status area with a given style type
function setStatus(message, type) {
  statusMessage.textContent = message;
  statusMessage.className = "status-message" + (type ? " " + type : "");
}

// Helper: reset result cards to default placeholders
function resetResults() {
  attendedResult.textContent = "-";
  absentResult.textContent = "-";
  percentageResult.textContent = "-";
  leavesResult.textContent = "-";
}

// Main calculation function (called when "Calculate Attendance" is clicked)
function calculateAttendance() {
  const totalValue = totalClassesInput.value.trim();
  const absentValue = absentClassesInput.value.trim();

  // Validation: inputs must not be empty and must be numeric integers
  if (totalValue === "" || absentValue === "") {
    setStatus("Please enter valid numeric values for total and absent classes.", "error");
    resetResults();
    return;
  }

  const total = Number(totalValue);
  const absent = Number(absentValue);

  // Accept only finite numbers (numeric values)
  if (!Number.isFinite(total) || !Number.isFinite(absent)) {
    setStatus("Please enter valid numeric values only.", "error");
    resetResults();
    return;
  }

  // Validation rules from the requirement
  if (!Number.isInteger(total) || !Number.isInteger(absent)) {
    setStatus("Please enter whole numbers only (no decimals).", "error");
    resetResults();
    return;
  }
  if (total <= 0) {
    setStatus("Total classes must be greater than 0.", "error");
    resetResults();
    return;
  }
  if (absent < 0) {
    setStatus("Absent classes cannot be negative.", "error");
    resetResults();
    return;
  }
  if (absent > total) {
    setStatus("Absent classes cannot be greater than total classes.", "error");
    resetResults();
    return;
  }

  // Calculations
  // Attended Classes = Total Classes - Absent Classes
  const attended = total - absent;

  // Attendance Percentage = (Attended Classes / Total Classes) × 100
  const percentage = (attended / total) * 100;

  // Additional leaves allowed while maintaining at least 75% attendance:
  // maximum total classes that can be missed = floor(attended / 0.75) - total (cannot be negative)
  let additionalLeavesAllowed = Math.floor(attended / 0.75) - total;
  if (additionalLeavesAllowed < 0) {
    additionalLeavesAllowed = 0;
  }

  // Display results
  attendedResult.textContent = String(attended);
  absentResult.textContent = String(absent);
  percentageResult.textContent = percentage.toFixed(2) + "%";
  leavesResult.textContent = String(additionalLeavesAllowed);

  // Status message based on 75% requirement
  if (percentage >= 75) {
    setStatus("Your attendance is sufficient.", "success");
  } else {
    setStatus("Warning: Your attendance is below 75%.", "warning");
  }
}

// Clear function (resets inputs, results, and messages)
function clearForm() {
  totalClassesInput.value = "";
  absentClassesInput.value = "";
  resetResults();
  setStatus("", "");
  totalClassesInput.focus();
}

// Attach event listeners to buttons
calculateBtn.addEventListener("click", calculateAttendance);
clearBtn.addEventListener("click", clearForm);
