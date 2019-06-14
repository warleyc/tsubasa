/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestStageRewardUpdateComponent } from 'app/entities/m-quest-stage-reward/m-quest-stage-reward-update.component';
import { MQuestStageRewardService } from 'app/entities/m-quest-stage-reward/m-quest-stage-reward.service';
import { MQuestStageReward } from 'app/shared/model/m-quest-stage-reward.model';

describe('Component Tests', () => {
  describe('MQuestStageReward Management Update Component', () => {
    let comp: MQuestStageRewardUpdateComponent;
    let fixture: ComponentFixture<MQuestStageRewardUpdateComponent>;
    let service: MQuestStageRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestStageRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MQuestStageRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MQuestStageRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestStageRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MQuestStageReward(123);
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
        const entity = new MQuestStageReward();
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
