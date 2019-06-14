/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MWeeklyQuestStageRewardUpdateComponent } from 'app/entities/m-weekly-quest-stage-reward/m-weekly-quest-stage-reward-update.component';
import { MWeeklyQuestStageRewardService } from 'app/entities/m-weekly-quest-stage-reward/m-weekly-quest-stage-reward.service';
import { MWeeklyQuestStageReward } from 'app/shared/model/m-weekly-quest-stage-reward.model';

describe('Component Tests', () => {
  describe('MWeeklyQuestStageReward Management Update Component', () => {
    let comp: MWeeklyQuestStageRewardUpdateComponent;
    let fixture: ComponentFixture<MWeeklyQuestStageRewardUpdateComponent>;
    let service: MWeeklyQuestStageRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MWeeklyQuestStageRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MWeeklyQuestStageRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MWeeklyQuestStageRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MWeeklyQuestStageRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MWeeklyQuestStageReward(123);
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
        const entity = new MWeeklyQuestStageReward();
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
