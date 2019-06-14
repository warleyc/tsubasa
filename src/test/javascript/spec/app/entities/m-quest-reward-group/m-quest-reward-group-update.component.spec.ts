/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestRewardGroupUpdateComponent } from 'app/entities/m-quest-reward-group/m-quest-reward-group-update.component';
import { MQuestRewardGroupService } from 'app/entities/m-quest-reward-group/m-quest-reward-group.service';
import { MQuestRewardGroup } from 'app/shared/model/m-quest-reward-group.model';

describe('Component Tests', () => {
  describe('MQuestRewardGroup Management Update Component', () => {
    let comp: MQuestRewardGroupUpdateComponent;
    let fixture: ComponentFixture<MQuestRewardGroupUpdateComponent>;
    let service: MQuestRewardGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestRewardGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MQuestRewardGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MQuestRewardGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestRewardGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MQuestRewardGroup(123);
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
        const entity = new MQuestRewardGroup();
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
