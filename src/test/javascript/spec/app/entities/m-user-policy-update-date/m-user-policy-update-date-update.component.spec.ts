/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MUserPolicyUpdateDateUpdateComponent } from 'app/entities/m-user-policy-update-date/m-user-policy-update-date-update.component';
import { MUserPolicyUpdateDateService } from 'app/entities/m-user-policy-update-date/m-user-policy-update-date.service';
import { MUserPolicyUpdateDate } from 'app/shared/model/m-user-policy-update-date.model';

describe('Component Tests', () => {
  describe('MUserPolicyUpdateDate Management Update Component', () => {
    let comp: MUserPolicyUpdateDateUpdateComponent;
    let fixture: ComponentFixture<MUserPolicyUpdateDateUpdateComponent>;
    let service: MUserPolicyUpdateDateService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MUserPolicyUpdateDateUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MUserPolicyUpdateDateUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MUserPolicyUpdateDateUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MUserPolicyUpdateDateService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MUserPolicyUpdateDate(123);
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
        const entity = new MUserPolicyUpdateDate();
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
