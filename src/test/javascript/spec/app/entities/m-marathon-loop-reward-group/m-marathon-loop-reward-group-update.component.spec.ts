/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonLoopRewardGroupUpdateComponent } from 'app/entities/m-marathon-loop-reward-group/m-marathon-loop-reward-group-update.component';
import { MMarathonLoopRewardGroupService } from 'app/entities/m-marathon-loop-reward-group/m-marathon-loop-reward-group.service';
import { MMarathonLoopRewardGroup } from 'app/shared/model/m-marathon-loop-reward-group.model';

describe('Component Tests', () => {
  describe('MMarathonLoopRewardGroup Management Update Component', () => {
    let comp: MMarathonLoopRewardGroupUpdateComponent;
    let fixture: ComponentFixture<MMarathonLoopRewardGroupUpdateComponent>;
    let service: MMarathonLoopRewardGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonLoopRewardGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMarathonLoopRewardGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMarathonLoopRewardGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonLoopRewardGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMarathonLoopRewardGroup(123);
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
        const entity = new MMarathonLoopRewardGroup();
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
