/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonQuestStageRewardUpdateComponent } from 'app/entities/m-marathon-quest-stage-reward/m-marathon-quest-stage-reward-update.component';
import { MMarathonQuestStageRewardService } from 'app/entities/m-marathon-quest-stage-reward/m-marathon-quest-stage-reward.service';
import { MMarathonQuestStageReward } from 'app/shared/model/m-marathon-quest-stage-reward.model';

describe('Component Tests', () => {
  describe('MMarathonQuestStageReward Management Update Component', () => {
    let comp: MMarathonQuestStageRewardUpdateComponent;
    let fixture: ComponentFixture<MMarathonQuestStageRewardUpdateComponent>;
    let service: MMarathonQuestStageRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonQuestStageRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMarathonQuestStageRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMarathonQuestStageRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonQuestStageRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMarathonQuestStageReward(123);
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
        const entity = new MMarathonQuestStageReward();
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
