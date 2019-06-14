/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTimeattackQuestStageRewardUpdateComponent } from 'app/entities/m-timeattack-quest-stage-reward/m-timeattack-quest-stage-reward-update.component';
import { MTimeattackQuestStageRewardService } from 'app/entities/m-timeattack-quest-stage-reward/m-timeattack-quest-stage-reward.service';
import { MTimeattackQuestStageReward } from 'app/shared/model/m-timeattack-quest-stage-reward.model';

describe('Component Tests', () => {
  describe('MTimeattackQuestStageReward Management Update Component', () => {
    let comp: MTimeattackQuestStageRewardUpdateComponent;
    let fixture: ComponentFixture<MTimeattackQuestStageRewardUpdateComponent>;
    let service: MTimeattackQuestStageRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTimeattackQuestStageRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTimeattackQuestStageRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTimeattackQuestStageRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTimeattackQuestStageRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTimeattackQuestStageReward(123);
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
        const entity = new MTimeattackQuestStageReward();
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
