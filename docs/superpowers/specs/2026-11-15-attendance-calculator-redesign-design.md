# Student Attendance Calculator Website Redesign — Short Design (Approved)

**Date:** 2026-11-15  
**Status:** Approved by user

## Intent & Why
Replace the existing simple calculator page with a clean, modern, student-friendly attendance calculator mini-project that is easy for a college student to use and for a Java student to explain in a viva.

## Goals
- Inputs: total classes conducted, classes absent.
- Outputs: classes attended, absent classes, attendance percentage (large/clear), additional leaves allowed while maintaining ≥75% attendance, and a status message.
- Responsive, professional-but-simple UI: white background with blue/purple accent, rounded inputs/buttons, clear result cards.
- Beginner-friendly, well-organized HTML/CSS/JavaScript with clear validation and calculation logic.

## Non-goals / Anti-goals
- No login, payment, AI, database, admin panels, or unnecessary features.
- No overly complicated design.

## Constraints
- Static site for GitHub Pages at `https://bhoomikasuresh21.github.io/java-project/`.
- Vanilla HTML/CSS/JS only.

## Approach
Single-page static site replacing `index.html` (with separate `style.css` and `script.js` for clarity/organization). Same 75% attendance rule:
- `attended = total - absent`
- `percentage = (attended / total) * 100`
- `additionalLeavesAllowed = max(0, floor(attended / 0.75) - total)` (max additional absences possible without dropping below 75%)

## Decisions (user-approved)
- Layout: header + subtitle, input section (Total, Absent, Calculate Attendance, Clear), results section/cards, status message.
- Status: “Your attendance is sufficient.” if ≥75%, else “Warning: Your attendance is below 75%”.
- Validation: total > 0, absent ≥ 0, absent ≤ total, numeric-only, with clear error messages.

## Left to Builder
- Exact spacing, typography, card styling, and accent shades (within clean white + blue/purple).
- Minor copy/label polish consistent with spec.
