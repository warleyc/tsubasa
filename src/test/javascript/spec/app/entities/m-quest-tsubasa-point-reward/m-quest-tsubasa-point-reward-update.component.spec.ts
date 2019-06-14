/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestTsubasaPointRewardUpdateComponent } from 'app/entities/m-quest-tsubasa-point-reward/m-quest-tsubasa-point-reward-update.component';
import { MQuestTsubasaPointRewardService } from 'app/entities/m-quest-tsubasa-point-reward/m-quest-tsubasa-point-reward.service';
import { MQuestTsubasaPointReward } from 'app/shared/model/m-quest-tsubasa-point-reward.model';

describe('Component Tests', () => {
  describe('MQuestTsubasaPointReward Management Update Component', () => {
    let comp: MQuestTsubasaPointRewardUpdateComponent;
    let fixture: ComponentFixture<MQuestTsubasaPointRewardUpdateComponent>;
    let service: MQuestTsubasaPointRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestTsubasaPointRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MQuestTsubasaPointRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MQuestTsubasaPointRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestTsubasaPointRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MQuestTsubasaPointReward(123);
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
        const entity = new MQuestTsubasaPointReward();
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
