const safeBtn = document.getElementById("safeBtn");
const riskyBtn = document.getElementById("riskyBtn");
const securityScore = document.getElementById("securityScore");
const totalFiles = document.getElementById("totalFiles");
const complexity = document.getElementById("complexity");
const technicalDebt = document.getElementById("technicalDebt");
const qualityGate = document.getElementById("qualityGate");
const compliance = document.getElementById("compliance");
const testRatio = document.getElementById("testRatio");
const issuesList = document.getElementById("issuesList");

const projects = {
    safe: {
        security: 100,
        files: 5,
        complexity: 6.0,
        debt: 0,
        quality: "PASSED",
        compliance: "COMPLIANT",
        ratio: 0.5,
        issues: []
    },

    risky: {
        security: 50,
        files: 5,
        complexity: 12.0,
        debt: 15,
        quality: "FAILED",
        compliance: "NON-COMPLIANT",
        ratio: 0.5,
        issues: [
            "Hardcoded credentials in PaymentService.java",
            "High-risk source file: PaymentService.java",
            "Insecure configuration: application.properties",
            "Exposed credentials in application.properties",
            "Vulnerable dependencies in pom.xml"
        ]
    }
};

function showProject(project) {
    const data = projects[project];

    securityScore.textContent = data.security + "/100";
    totalFiles.textContent = data.files;
    complexity.textContent = data.complexity.toFixed(1);
    technicalDebt.textContent = data.debt;
    qualityGate.textContent = data.quality;
    compliance.textContent = data.compliance;
    testRatio.textContent = data.ratio;

    issuesList.innerHTML = "";

    if (data.issues.length === 0) {
        const li = document.createElement("li");
        li.textContent = "No issues found.";
        issuesList.appendChild(li);
    } else {
        data.issues.forEach(function(issue) {
            const li = document.createElement("li");
            li.textContent = issue;
            issuesList.appendChild(li);
        });
    }

    safeBtn.classList.toggle("active", project === "safe");
    riskyBtn.classList.toggle("active", project === "risky");
}

safeBtn.addEventListener("click", function() {
    showProject("safe");
});

riskyBtn.addEventListener("click", function() {
    showProject("risky");
});

showProject("safe");


const hardcodedPassword = document.getElementById("hardcodedPassword");
const codeComplexity = document.getElementById("codeComplexity");
const complexityValue = document.getElementById("complexityValue");
const vulnerableDependencies = document.getElementById("vulnerableDependencies");
const runInspectionBtn = document.getElementById("runInspectionBtn");
const inspectionMessage = document.getElementById("inspectionMessage");

codeComplexity.addEventListener("input", function() {
    complexityValue.textContent = codeComplexity.value;
});

runInspectionBtn.addEventListener("click", function() {
    const password = hardcodedPassword.checked;
    const complexityNumber = Number(codeComplexity.value);
    const vulnerable = vulnerableDependencies.checked;

    const issues = [];

    if (password) {
        issues.push("Hardcoded credentials detected");
    }

    if (complexityNumber > 15) {
        issues.push("High-risk source file");
    }

    if (vulnerable) {
        issues.push("Vulnerable dependencies detected");
    }

    const score = Math.max(0, 100 - issues.length * 10);
    const securityPassed = !password && !vulnerable;
    const complexityPassed = complexityNumber <= 10;
    const testsPassed = true;
    const passed = securityPassed && complexityPassed && testsPassed;

    securityScore.textContent = score + "/100";
    complexity.textContent = complexityNumber.toFixed(1);
    qualityGate.textContent = passed ? "PASSED" : "FAILED";
    compliance.textContent = password ? "NON-COMPLIANT" : "COMPLIANT";
    technicalDebt.textContent = complexityNumber > 10 ? 10 : 0;

    issuesList.innerHTML = "";

    if (issues.length === 0) {
        const li = document.createElement("li");
        li.textContent = "No security issues found.";
        issuesList.appendChild(li);
    } else {
        issues.forEach(function(issue) {
            const li = document.createElement("li");
            li.textContent = issue;
            issuesList.appendChild(li);
        });
    }

    inspectionMessage.textContent = passed
        ? "Inspection completed: Project is ready!"
        : "Inspection completed: Issues detected!";

    safeBtn.classList.remove("active");
    riskyBtn.classList.remove("active");
});

