/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestSpecialRewardUpdateComponent } from 'app/entities/m-quest-special-reward/m-quest-special-reward-update.component';
import { MQuestSpecialRewardService } from 'app/entities/m-quest-special-reward/m-quest-special-reward.service';
import { MQuestSpecialReward } from 'app/shared/model/m-quest-special-reward.model';

describe('Component Tests', () => {
  describe('MQuestSpecialReward Management Update Component', () => {
    let comp: MQuestSpecialRewardUpdateComponent;
    let fixture: ComponentFixture<MQuestSpecialRewardUpdateComponent>;
    let service: MQuestSpecialRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestSpecialRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MQuestSpecialRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MQuestSpecialRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestSpecialRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MQuestSpecialReward(123);
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
        const entity = new MQuestSpecialReward();
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
