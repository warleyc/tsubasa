/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCharacterScoreCutUpdateComponent } from 'app/entities/m-character-score-cut/m-character-score-cut-update.component';
import { MCharacterScoreCutService } from 'app/entities/m-character-score-cut/m-character-score-cut.service';
import { MCharacterScoreCut } from 'app/shared/model/m-character-score-cut.model';

describe('Component Tests', () => {
  describe('MCharacterScoreCut Management Update Component', () => {
    let comp: MCharacterScoreCutUpdateComponent;
    let fixture: ComponentFixture<MCharacterScoreCutUpdateComponent>;
    let service: MCharacterScoreCutService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCharacterScoreCutUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MCharacterScoreCutUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MCharacterScoreCutUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCharacterScoreCutService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCharacterScoreCut(123);
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
        const entity = new MCharacterScoreCut();
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
