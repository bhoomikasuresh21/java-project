// Attendly - Student Attendance Calculator
// Core calculation and validation logic preserved; UI/insight/visualization added.

const form = document.getElementById("attendanceForm");
const totalClassesInput = document.getElementById("totalClasses");
const absentClassesInput = document.getElementById("absentClasses");
const clearBtn = document.getElementById("clearBtn");

const attendedResult = document.getElementById("attendedResult");
const absentResult = document.getElementById("absentResult");
const percentageResult = document.getElementById("percentageResult");
const leavesResult = document.getElementById("leavesResult");
const statusMessage = document.getElementById("statusMessage"); // accessible status text

const messageArea = document.getElementById("messageArea");
const insightCard = document.getElementById("insightCard");
const insightText = document.getElementById("insightText");
const resultsSection = document.getElementById("resultsSection");

const ringPercentage = document.getElementById("ringPercentage");
const progressRing = document.getElementById("progressRing");
const overviewStatusPill = document.getElementById("overviewStatusPill");
const overviewHint = document.getElementById("overviewHint");

const RING_RADIUS = 52;
const RING_CIRCUMFERENCE = 2 * Math.PI * RING_RADIUS; // ~326.7256

function setStatus(message, type) {
  statusMessage.textContent = message || "";
  messageArea.innerHTML = "";
  if (!message) return;
  const div = document.createElement("div");
  div.className = "message " + (type || "");
  div.textContent = message;
  messageArea.appendChild(div);
}

function resetResults() {
  attendedResult.textContent = "-";
  absentResult.textContent = "-";
  percentageResult.textContent = "-";
  leavesResult.textContent = "-";
  insightText.textContent = "";
  insightCard.classList.add("hidden");
  resultsSection.classList.remove("show");
}

function updateRing(percentage, state) {
  const clamped = Math.max(0, Math.min(100, percentage));
  const offset = RING_CIRCUMFERENCE - (clamped / 100) * RING_CIRCUMFERENCE;
  progressRing.style.strokeDasharray = String(RING_CIRCUMFERENCE);
  progressRing.style.strokeDashoffset = String(offset);
  ringPercentage.textContent = percentage.toFixed(2) + "%";

  let color = "#6366f1"; // default indigo
  if (state === "safe") color = "#16a34a";
  else if (state === "caution") color = "#2563eb";
  else if (state === "warning") color = "#dc2626";
  progressRing.style.stroke = color;
}

function updateOverviewState(percentage) {
  if (percentage >= 85) {
    overviewStatusPill.textContent = "Healthy";
    overviewStatusPill.className = "status-pill safe";
    overviewHint.textContent = "You're well above the requirement. Keep up the consistency.";
    return "safe";
  }
  if (percentage >= 75) {
    overviewStatusPill.textContent = "Minimum met";
    overviewStatusPill.className = "status-pill caution";
    overviewHint.textContent = "You're meeting the 75% requirement. Plan leaves carefully.";
    return "caution";
  }
  overviewStatusPill.textContent = "Below 75%";
  overviewStatusPill.className = "status-pill warning";
  overviewHint.textContent = "Your attendance is below the required threshold. Focus on attending upcoming classes.";
  return "warning";
}

function resetOverview() {
  progressRing.style.strokeDasharray = String(RING_CIRCUMFERENCE);
  progressRing.style.strokeDashoffset = String(RING_CIRCUMFERENCE);
  progressRing.style.stroke = "#6366f1";
  ringPercentage.textContent = "--%";
  overviewStatusPill.textContent = "Awaiting input";
  overviewStatusPill.className = "status-pill neutral";
  overviewHint.textContent = "Enter your class details to see your attendance health.";
}

// Main calculation function (core logic preserved)
function calculateAttendance() {
  const totalValue = totalClassesInput.value.trim();
  const absentValue = absentClassesInput.value.trim();

  // Validation: inputs must not be empty and must be numeric values
  if (totalValue === "" || absentValue === "") {
    setStatus("Please enter valid numeric values for total and absent classes.", "error");
    resetResults();
    resetOverview();
    return;
  }

  const total = Number(totalValue);
  const absent = Number(absentValue);

  if (!Number.isFinite(total) || !Number.isFinite(absent)) {
    setStatus("Please enter valid numeric values only.", "error");
    resetResults();
    resetOverview();
    return;
  }

  // Keep validation behavior: require whole-number class counts
  if (!Number.isInteger(total) || !Number.isInteger(absent)) {
    setStatus("Please enter whole numbers only (no decimals).", "error");
    resetResults();
    resetOverview();
    return;
  }
  if (total <= 0) {
    setStatus("Total classes must be greater than 0.", "error");
    resetResults();
    resetOverview();
    return;
  }
  if (absent < 0) {
    setStatus("Absent classes cannot be negative.", "error");
    resetResults();
    resetOverview();
    return;
  }
  if (absent > total) {
    setStatus("Absent classes cannot be greater than total classes.", "error");
    resetResults();
    resetOverview();
    return;
  }

  // Core calculations (unchanged)
  // Attended Classes = Total Classes - Absent Classes
  const attended = total - absent;
  // Attendance Percentage = (Attended Classes / Total Classes) × 100
  const percentage = (attended / total) * 100;
  // Additional leaves allowed while maintaining at least 75% attendance
  let additionalLeavesAllowed = Math.floor(attended / 0.75) - total;
  if (additionalLeavesAllowed < 0) additionalLeavesAllowed = 0;

  // Render results
  attendedResult.textContent = String(attended);
  absentResult.textContent = String(absent);
  percentageResult.textContent = percentage.toFixed(2) + "%";
  leavesResult.textContent = String(additionalLeavesAllowed);

  // Status message based on 75% requirement (core behavior preserved)
  if (percentage >= 75) {
    setStatus("Your attendance is sufficient.", "success");
  } else {
    setStatus("Warning: Your attendance is below 75%.", "warning");
  }

  // Progress ring and overview state
  const state = updateOverviewState(percentage);
  updateRing(percentage, state);

  // Smart leave insight (derived from same totals/logic, no change to core formulas)
  if (percentage >= 75) {
    insightText.textContent = `You're currently above the 75% requirement and can safely take ${additionalLeavesAllowed} more classes off.`;
  } else {
    // Classes needed to reach 75% if attending upcoming classes: ceil((0.75*total - attended) / 0.25)
    const classesNeeded = Math.max(0, Math.ceil(3 * total - 4 * attended));
    insightText.textContent = `Your attendance is below the required 75%. Attend the next ${classesNeeded} classes to improve your attendance.`;
  }
  insightCard.classList.remove("hidden");

  // Trigger smooth card reveal animation
  resultsSection.classList.remove("show");
  void resultsSection.offsetWidth; // reflow to restart animation
  resultsSection.classList.add("show");
}

// Clear/reset function
function clearForm() {
  form.reset();
  resetResults();
  resetOverview();
  setStatus("", "");
  totalClassesInput.focus();
}

// Event listeners
form.addEventListener("submit", function (e) {
  e.preventDefault();
  calculateAttendance();
});
clearBtn.addEventListener("click", clearForm);

// Initialize ring state
resetOverview();
