/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpGradeUpdateComponent } from 'app/entities/m-pvp-grade/m-pvp-grade-update.component';
import { MPvpGradeService } from 'app/entities/m-pvp-grade/m-pvp-grade.service';
import { MPvpGrade } from 'app/shared/model/m-pvp-grade.model';

describe('Component Tests', () => {
  describe('MPvpGrade Management Update Component', () => {
    let comp: MPvpGradeUpdateComponent;
    let fixture: ComponentFixture<MPvpGradeUpdateComponent>;
    let service: MPvpGradeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpGradeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MPvpGradeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MPvpGradeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPvpGradeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MPvpGrade(123);
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
        const entity = new MPvpGrade();
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
