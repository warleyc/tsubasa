/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGoalJudgementUpdateComponent } from 'app/entities/m-goal-judgement/m-goal-judgement-update.component';
import { MGoalJudgementService } from 'app/entities/m-goal-judgement/m-goal-judgement.service';
import { MGoalJudgement } from 'app/shared/model/m-goal-judgement.model';

describe('Component Tests', () => {
  describe('MGoalJudgement Management Update Component', () => {
    let comp: MGoalJudgementUpdateComponent;
    let fixture: ComponentFixture<MGoalJudgementUpdateComponent>;
    let service: MGoalJudgementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGoalJudgementUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGoalJudgementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGoalJudgementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGoalJudgementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGoalJudgement(123);
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
        const entity = new MGoalJudgement();
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
