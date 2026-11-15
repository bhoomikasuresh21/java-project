# Student Attendance Calculator Website Redesign Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Replace the live calculator page with a clean, modern, responsive, beginner-friendly Student Attendance Calculator (HTML/CSS/JS) that computes attendance stats and additional leaves allowed at 75%.

**Architecture:** A single static page (`index.html`) styled by `style.css` and powered by vanilla JavaScript in `script.js`. UI sections: header, inputs, results/cards, and status message. No frameworks or backend.

**Tech Stack:** HTML5, CSS3, vanilla JavaScript.

**Spec:** `docs/superpowers/specs/2026-11-15-attendance-calculator-redesign-design.md`

## Global Constraints

- Simple student mini-project: no login, payment, AI, database, admin panels, or unnecessary features.
- Responsive, clean white background with blue/purple accent; rounded inputs/buttons; large attendance percentage; clear result cards; not overcomplicated.
- Beginner-friendly and well-organized code with comments suitable for viva explanation.
- Calculations: `Attended = Total - Absent`; `Percentage = (Attended / Total) * 100`; `Additional Leaves Allowed = max(0, floor(Attended / 0.75) - Total)`.
- Validation: total > 0, absent >= 0, absent <= total, numeric-only inputs; show clear error messages for invalid input.

## Review Focus

- Non-numeric/empty inputs show a clear error and do not produce results (e.g. "Please enter valid numeric values.").
- Boundary: total = 1, absent = 0 yields 100.00%, sufficient status, and correct additional leaves (`floor(1/0.75)-1 = 0`).
- Boundary: absent == total yields 0.00%, 0 attended, warning status (<75%), and 0 additional leaves.
- Below-threshold case (e.g. total 100, absent 30) shows warning "Warning: Your attendance is below 75%." and 0 additional leaves.
- At/above-threshold case (e.g. total 100, absent 20) shows "Your attendance is sufficient." and correct additional leaves (6).

---

### Task 1: Build the redesigned calculator UI and logic

**Files:**
- Create: `style.css`
- Create: `script.js`
- Modify: `index.html` (replace existing content)

**Interfaces:**
- Consumes: none (standalone static page).
- Produces: `index.html` linking `style.css` and `script.js`; DOM elements with stable IDs/classes used by script: inputs `totalClasses`, `absentClasses`; buttons `calculateBtn`, `clearBtn`; result fields `attendedResult`, `absentResult`, `percentageResult`, `leavesResult`; status/error display `statusMessage`; JS functions `calculateAttendance()` and `clearForm()` wired to button clicks (or equivalent event listeners) implementing spec calculations and validation.

- [ ] **Step 1: Create `index.html` structure**

Write `index.html` with: header ("Student Attendance Calculator", subtitle "Calculate your attendance and remaining leave allowance"), input section (labels/inputs for Total Classes and Absent Classes, buttons "Calculate Attendance" and "Clear"), results section showing Attended Classes, Absent Classes, Attendance Percentage (large display), Additional Leaves Allowed as clear cards, and a status/error message area. Link `style.css` in `<head>` and `script.js` before `</body>`. Use semantic, readable markup.

- [ ] **Step 2: Create `style.css` styling**

Write responsive CSS: clean white page background, blue/purple accent for header/buttons/highlights, rounded input boxes and buttons, large attendance percentage display, clear result cards/grid, readable spacing for desktop and mobile (media query as needed). Keep design simple and modern.

- [ ] **Step 3: Create `script.js` calculation, validation, and UI updates**

Implement logic:
- Read and parse numeric inputs.
- Validate: numeric-only and non-empty; total > 0; absent >= 0; absent <= total. On invalid input show a clear error message and do not render calculated results.
- Compute: attended = total - absent; percentage = (attended / total) * 100; additionalLeavesAllowed = Math.max(0, Math.floor(attended / 0.75) - total).
- Render attended, absent, percentage formatted to 2 decimal places with `%`, and additional leaves.
- Set status: if percentage >= 75 show "Your attendance is sufficient." (positive styling); if percentage < 75 show "Warning: Your attendance is below 75%." (warning styling).
- `clearForm()` resets inputs, results, and messages.
Wire `calculateAttendance()` to Calculate button and `clearForm()` to Clear button. Add brief comments explaining logic for viva.

- [ ] **Step 4: Verify behavior locally**

Open `index.html` in a browser and confirm:
- Layout matches required sections and is responsive/simple.
- Invalid inputs (empty, non-numeric, total <= 0, absent < 0, absent > total) show clear errors.
- Examples: (100,20) -> attended 80, 80.00%, leaves 6, sufficient; (100,30) -> 70, 70.00%, leaves 0, warning; (1,0) -> 1, 100.00%, leaves 0, sufficient; (10,10) -> 0, 0.00%, leaves 0, warning.
- Clear resets all fields/messages.

- [ ] **Step 5: Commit**

```bash
git add index.html style.css script.js
git commit -m "feat: redesign attendance calculator website"
```

---

### Task 2: Publish updated site to GitHub Pages

**Files:**
- Modify (git state): repository at `C:\Users\Lenovo\AppData\Local\Temp\opencode\attendance-calculator-site`

**Interfaces:**
- Consumes: committed redesign from Task 1.
- Produces: updated `main` branch pushed to `https://github.com/bhoomikasuresh21/java-project.git` so GitHub Pages serves the new version.

- [ ] **Step 1: Push changes**

Run:
```bash
git push origin main
```
Expected: push succeeds and remote `main` updated.

- [ ] **Step 2: Verify live site**

After Pages redeploys (1–2 minutes), open `https://bhoomikasuresh21.github.io/java-project/` and confirm the redesigned UI and calculations appear (hard refresh if needed).

- [ ] **Step 3: Commit/push spec and plan docs (optional but recommended)**

```bash
git add docs/superpowers/specs/2026-11-15-attendance-calculator-redesign-design.md docs/superpowers/plans/2026-11-15-attendance-calculator-redesign.md
git commit -m "docs: add redesign spec and plan"
git push origin main
```
Expected: docs recorded in repo.

- [ ] **Step 4: Report completion**

Report: files changed, live URL, and brief verification notes.
