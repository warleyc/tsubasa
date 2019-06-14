/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MNgWordUpdateComponent } from 'app/entities/m-ng-word/m-ng-word-update.component';
import { MNgWordService } from 'app/entities/m-ng-word/m-ng-word.service';
import { MNgWord } from 'app/shared/model/m-ng-word.model';

describe('Component Tests', () => {
  describe('MNgWord Management Update Component', () => {
    let comp: MNgWordUpdateComponent;
    let fixture: ComponentFixture<MNgWordUpdateComponent>;
    let service: MNgWordService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MNgWordUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MNgWordUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MNgWordUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MNgWordService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MNgWord(123);
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
        const entity = new MNgWord();
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
