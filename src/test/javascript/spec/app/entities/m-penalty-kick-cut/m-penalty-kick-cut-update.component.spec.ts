/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPenaltyKickCutUpdateComponent } from 'app/entities/m-penalty-kick-cut/m-penalty-kick-cut-update.component';
import { MPenaltyKickCutService } from 'app/entities/m-penalty-kick-cut/m-penalty-kick-cut.service';
import { MPenaltyKickCut } from 'app/shared/model/m-penalty-kick-cut.model';

describe('Component Tests', () => {
  describe('MPenaltyKickCut Management Update Component', () => {
    let comp: MPenaltyKickCutUpdateComponent;
    let fixture: ComponentFixture<MPenaltyKickCutUpdateComponent>;
    let service: MPenaltyKickCutService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPenaltyKickCutUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MPenaltyKickCutUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MPenaltyKickCutUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPenaltyKickCutService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MPenaltyKickCut(123);
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
        const entity = new MPenaltyKickCut();
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
