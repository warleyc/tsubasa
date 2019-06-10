/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionBeforeShootCutInCharacterNumDetailComponent } from 'app/entities/m-gacha-rendition-before-shoot-cut-in-character-num/m-gacha-rendition-before-shoot-cut-in-character-num-detail.component';
import { MGachaRenditionBeforeShootCutInCharacterNum } from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-character-num.model';

describe('Component Tests', () => {
  describe('MGachaRenditionBeforeShootCutInCharacterNum Management Detail Component', () => {
    let comp: MGachaRenditionBeforeShootCutInCharacterNumDetailComponent;
    let fixture: ComponentFixture<MGachaRenditionBeforeShootCutInCharacterNumDetailComponent>;
    const route = ({
      data: of({ mGachaRenditionBeforeShootCutInCharacterNum: new MGachaRenditionBeforeShootCutInCharacterNum(123) })
    } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionBeforeShootCutInCharacterNumDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGachaRenditionBeforeShootCutInCharacterNumDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionBeforeShootCutInCharacterNumDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGachaRenditionBeforeShootCutInCharacterNum).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
