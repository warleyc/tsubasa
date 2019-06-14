/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonRankingRewardGroupUpdateComponent } from 'app/entities/m-marathon-ranking-reward-group/m-marathon-ranking-reward-group-update.component';
import { MMarathonRankingRewardGroupService } from 'app/entities/m-marathon-ranking-reward-group/m-marathon-ranking-reward-group.service';
import { MMarathonRankingRewardGroup } from 'app/shared/model/m-marathon-ranking-reward-group.model';

describe('Component Tests', () => {
  describe('MMarathonRankingRewardGroup Management Update Component', () => {
    let comp: MMarathonRankingRewardGroupUpdateComponent;
    let fixture: ComponentFixture<MMarathonRankingRewardGroupUpdateComponent>;
    let service: MMarathonRankingRewardGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonRankingRewardGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMarathonRankingRewardGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMarathonRankingRewardGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonRankingRewardGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMarathonRankingRewardGroup(123);
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
        const entity = new MMarathonRankingRewardGroup();
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
