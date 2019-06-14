/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MUserRankUpdateComponent } from 'app/entities/m-user-rank/m-user-rank-update.component';
import { MUserRankService } from 'app/entities/m-user-rank/m-user-rank.service';
import { MUserRank } from 'app/shared/model/m-user-rank.model';

describe('Component Tests', () => {
  describe('MUserRank Management Update Component', () => {
    let comp: MUserRankUpdateComponent;
    let fixture: ComponentFixture<MUserRankUpdateComponent>;
    let service: MUserRankService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MUserRankUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MUserRankUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MUserRankUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MUserRankService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MUserRank(123);
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
        const entity = new MUserRank();
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
