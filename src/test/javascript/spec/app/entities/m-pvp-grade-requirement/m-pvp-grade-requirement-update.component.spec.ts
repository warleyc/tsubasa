/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpGradeRequirementUpdateComponent } from 'app/entities/m-pvp-grade-requirement/m-pvp-grade-requirement-update.component';
import { MPvpGradeRequirementService } from 'app/entities/m-pvp-grade-requirement/m-pvp-grade-requirement.service';
import { MPvpGradeRequirement } from 'app/shared/model/m-pvp-grade-requirement.model';

describe('Component Tests', () => {
  describe('MPvpGradeRequirement Management Update Component', () => {
    let comp: MPvpGradeRequirementUpdateComponent;
    let fixture: ComponentFixture<MPvpGradeRequirementUpdateComponent>;
    let service: MPvpGradeRequirementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpGradeRequirementUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MPvpGradeRequirementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MPvpGradeRequirementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPvpGradeRequirementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MPvpGradeRequirement(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MPvpGradeRequirement();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
