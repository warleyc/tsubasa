/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestGoalRewardUpdateComponent } from 'app/entities/m-quest-goal-reward/m-quest-goal-reward-update.component';
import { MQuestGoalRewardService } from 'app/entities/m-quest-goal-reward/m-quest-goal-reward.service';
import { MQuestGoalReward } from 'app/shared/model/m-quest-goal-reward.model';

describe('Component Tests', () => {
  describe('MQuestGoalReward Management Update Component', () => {
    let comp: MQuestGoalRewardUpdateComponent;
    let fixture: ComponentFixture<MQuestGoalRewardUpdateComponent>;
    let service: MQuestGoalRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestGoalRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MQuestGoalRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MQuestGoalRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestGoalRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MQuestGoalReward(123);
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
        const entity = new MQuestGoalReward();
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
